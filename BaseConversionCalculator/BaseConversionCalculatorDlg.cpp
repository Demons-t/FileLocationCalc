
// BaseConversionCalculatorDlg.cpp: 实现文件
//

#include "stdafx.h"
#include "BaseConversionCalculator.h"
#include "BaseConversionCalculatorDlg.h"
#include "afxdialogex.h"
#include "resource.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#endif


// CBaseConversionCalculatorDlg 对话框



CBaseConversionCalculatorDlg::CBaseConversionCalculatorDlg(CWnd* pParent /*=nullptr*/)
	: CDialogEx(IDD_BASECONVERSIONCALCULATOR_DIALOG, pParent)
{
	m_hIcon = AfxGetApp()->LoadIcon(IDR_MAINFRAME);
}

void CBaseConversionCalculatorDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
	DDX_Control(pDX, IDC_EDIT1, m_editVA);
	DDX_Control(pDX, IDC_EDIT2, m_editRVA);
	DDX_Control(pDX, IDC_EDIT3, m_editFOA);
	DDX_Control(pDX, IDC_EDIT4, m_editFilePath);
}

DWORD CBaseConversionCalculatorDlg::RvaToFoa(char * pFileData, DWORD dwRva)
{
	PIMAGE_DOS_HEADER pDos = (PIMAGE_DOS_HEADER)pFileData;
	PIMAGE_NT_HEADERS pNt = (PIMAGE_NT_HEADERS)(pDos->e_lfanew + pFileData);
	if (dwRva <= pNt->OptionalHeader.SizeOfHeaders)
	{
		return dwRva;
	}
	PIMAGE_SECTION_HEADER pScnHdr = IMAGE_FIRST_SECTION(pNt);
	DWORD dwCount = pNt->FileHeader.NumberOfSections;
	for (int i = 0; i < dwCount; i++)
	{
		if (dwRva >= pScnHdr[i].VirtualAddress
			&&
			dwRva < pScnHdr[i].VirtualAddress + pScnHdr[i].SizeOfRawData)
		{
			return dwRva - pScnHdr[i].VirtualAddress + pScnHdr[i].PointerToRawData;
		}
		pScnHdr++;
	}
	return 0;
}

DWORD CBaseConversionCalculatorDlg::RvaToVa(char * pFileData, DWORD dwRva)
{
	PIMAGE_DOS_HEADER pDos = (PIMAGE_DOS_HEADER)pFileData;
	PIMAGE_NT_HEADERS pNt = (PIMAGE_NT_HEADERS)(pDos->e_lfanew + pFileData);
	return dwRva + pNt->OptionalHeader.ImageBase;
}

DWORD CBaseConversionCalculatorDlg::VaToRva(char * pFileData, DWORD dwVa)
{
	PIMAGE_DOS_HEADER pDos = (PIMAGE_DOS_HEADER)pFileData;
	PIMAGE_NT_HEADERS pNt = (PIMAGE_NT_HEADERS)(pDos->e_lfanew + pFileData);
	return dwVa - pNt->OptionalHeader.ImageBase;
}

DWORD CBaseConversionCalculatorDlg::VaToFoa(char * pFileData, DWORD dwVa)
{
	DWORD dwRva = VaToRva(pFileData, dwVa);
	if (!dwRva)
	{
		return 0;
	}
	return RvaToFoa(pFileData, dwRva);
}

DWORD CBaseConversionCalculatorDlg::FoaToRva(char * pFileData, DWORD dwFoa)
{
	PIMAGE_DOS_HEADER pDos = (PIMAGE_DOS_HEADER)pFileData;
	PIMAGE_NT_HEADERS pNt = (PIMAGE_NT_HEADERS)(pDos->e_lfanew + pFileData);
	if (dwFoa <= pNt->OptionalHeader.SizeOfHeaders)
	{
		return dwFoa;
	}
	PIMAGE_SECTION_HEADER pScnHdr = IMAGE_FIRST_SECTION(pNt);
	DWORD dwCount = pNt->FileHeader.NumberOfSections;
	for (int i = 0; i < dwCount; i++)
	{
		if (dwFoa >= pScnHdr[i].PointerToRawData
			&&
			dwFoa < pScnHdr[i].PointerToRawData + pScnHdr[i].SizeOfRawData)
		{
			return dwFoa - pScnHdr[i].PointerToRawData + pScnHdr[i].VirtualAddress;
		}
		pScnHdr++;
	}
	return 0;
}

DWORD CBaseConversionCalculatorDlg::FoaToVa(char * pFileData, DWORD dwFoa)
{
	DWORD dwRva = FoaToRva(pFileData, dwFoa);
	if (!dwRva)
	{
		return 0;
	}
	return RvaToVa(pFileData, dwRva);
}

void CBaseConversionCalculatorDlg::ObtainFilePath(HDROP hDropInfo)
{
	m_strPath.GetBufferSetLength(MAX_PATH);
	DragQueryFile(hDropInfo, 0, (TCHAR*)(const TCHAR*)m_strPath, MAX_PATH);
	m_editFilePath.SetWindowTextW(m_strPath);
	DragFinish(hDropInfo);
}

void CBaseConversionCalculatorDlg::OpenFile()
{
	m_hFile = CreateFile(m_strPath, GENERIC_READ, FILE_SHARE_READ, NULL, OPEN_EXISTING, FILE_ATTRIBUTE_NORMAL, NULL);
	if (m_hFile == INVALID_HANDLE_VALUE)
	{
		CString strErr;
		strErr.Format(_T("error: %s\n"), GetLastError());
		OutputDebugString(strErr);
		return;
	}

	DWORD dwFileSize = GetFileSize(m_hFile, NULL);
	m_pBuff = new char[dwFileSize];
	DWORD dwRead = 0;
	ReadFile(m_hFile, m_pBuff, dwFileSize, &dwRead, 0);
	CloseHandle(m_hFile);
}



BEGIN_MESSAGE_MAP(CBaseConversionCalculatorDlg, CDialogEx)
	ON_WM_PAINT()
	ON_WM_QUERYDRAGICON()
	ON_BN_CLICKED(IDC_BUTTON1, &CBaseConversionCalculatorDlg::VA)
	ON_BN_CLICKED(IDC_BUTTON2, &CBaseConversionCalculatorDlg::RVA)
	ON_BN_CLICKED(IDC_BUTTON3, &CBaseConversionCalculatorDlg::FOA)
	ON_BN_CLICKED(IDC_BUTTON4, &CBaseConversionCalculatorDlg::StratCalculate)
	ON_WM_DROPFILES()
END_MESSAGE_MAP()


// CBaseConversionCalculatorDlg 消息处理程序

BOOL CBaseConversionCalculatorDlg::OnInitDialog()
{
	CDialogEx::OnInitDialog();

	// 设置此对话框的图标。  当应用程序主窗口不是对话框时，框架将自动
	//  执行此操作
	SetIcon(m_hIcon, TRUE);			// 设置大图标
	SetIcon(m_hIcon, FALSE);		// 设置小图标

	// TODO: 在此添加额外的初始化代码
	GetDlgItem(IDC_EDIT1)->EnableWindow(FALSE);
	GetDlgItem(IDC_EDIT2)->EnableWindow(TRUE);
	GetDlgItem(IDC_EDIT3)->EnableWindow(FALSE);

	return TRUE;  // 除非将焦点设置到控件，否则返回 TRUE
}

// 如果向对话框添加最小化按钮，则需要下面的代码
//  来绘制该图标。  对于使用文档/视图模型的 MFC 应用程序，
//  这将由框架自动完成。

void CBaseConversionCalculatorDlg::OnPaint()
{
	if (IsIconic())
	{
		CPaintDC dc(this); // 用于绘制的设备上下文

		SendMessage(WM_ICONERASEBKGND, reinterpret_cast<WPARAM>(dc.GetSafeHdc()), 0);

		// 使图标在工作区矩形中居中
		int cxIcon = GetSystemMetrics(SM_CXICON);
		int cyIcon = GetSystemMetrics(SM_CYICON);
		CRect rect;
		GetClientRect(&rect);
		int x = (rect.Width() - cxIcon + 1) / 2;
		int y = (rect.Height() - cyIcon + 1) / 2;

		// 绘制图标
		dc.DrawIcon(x, y, m_hIcon);
	}
	else
	{
		CDialogEx::OnPaint();
	}
}

//当用户拖动最小化窗口时系统调用此函数取得光标
//显示。
HCURSOR CBaseConversionCalculatorDlg::OnQueryDragIcon()
{
	return static_cast<HCURSOR>(m_hIcon);
}



void CBaseConversionCalculatorDlg::VA()
{
	// TODO: 在此添加控件通知处理程序代码
	GetDlgItem(IDC_EDIT1)->EnableWindow(TRUE);
	GetDlgItem(IDC_EDIT2)->EnableWindow(FALSE);
	GetDlgItem(IDC_EDIT3)->EnableWindow(FALSE);
}


void CBaseConversionCalculatorDlg::RVA()
{
	// TODO: 在此添加控件通知处理程序代码
	GetDlgItem(IDC_EDIT1)->EnableWindow(FALSE);
	GetDlgItem(IDC_EDIT2)->EnableWindow(TRUE);
	GetDlgItem(IDC_EDIT3)->EnableWindow(FALSE);
}


void CBaseConversionCalculatorDlg::FOA()
{
	// TODO: 在此添加控件通知处理程序代码
	GetDlgItem(IDC_EDIT1)->EnableWindow(FALSE);
	GetDlgItem(IDC_EDIT2)->EnableWindow(FALSE);
	GetDlgItem(IDC_EDIT3)->EnableWindow(TRUE);
}


void CBaseConversionCalculatorDlg::StratCalculate()
{
	// TODO: 在此添加控件通知处理程序代码
	int VA = GetDlgItem(IDC_EDIT1)->IsWindowEnabled();
	int RVA = GetDlgItem(IDC_EDIT2)->IsWindowEnabled();
	int FOA = GetDlgItem(IDC_EDIT3)->IsWindowEnabled();
	if (!m_pBuff)
	{
		MessageBox(_T("无文件数据"));
		return;
	}
	if (VA)
	{
		CString strVA;
		m_editVA.GetWindowTextW(strVA);
		int nNum16;
		nNum16 = _tcstol(strVA, NULL, 16);
		int nNum;
		nNum = VaToFoa(m_pBuff, nNum16);
		if (nNum)
		{
			strVA.Format(_T("%08X"), nNum);
			m_editFOA.SetWindowText(strVA);
			nNum = VaToRva(m_pBuff, nNum16);
			strVA.Format(_T("%08X"), nNum);
			m_editRVA.SetWindowTextW(strVA);
		}
		else
		{
			m_editFOA.SetWindowText(_T("00000000"));
			m_editRVA.SetWindowText(_T("00000000"));
			m_editVA.SetWindowText(_T("00000000"));
			MessageBox(_T("非法地址"));
		}
	}
	else if (RVA)
	{
		CString strRVA;
		m_editRVA.GetWindowTextW(strRVA);
		int nNum16;
		nNum16 = _tcstol(strRVA, NULL, 16);
		int nNum;
		nNum = RvaToFoa(m_pBuff, nNum16);
		if (nNum)
		{
			strRVA.Format(_T("%08X"), nNum);
			m_editFOA.SetWindowTextW(strRVA);
			nNum = RvaToVa(m_pBuff, nNum16);
			strRVA.Format(_T("%08X"), nNum);
			m_editVA.SetWindowTextW(strRVA);
		}
		else
		{
			m_editFOA.SetWindowText(_T("00000000"));
			m_editRVA.SetWindowText(_T("00000000"));
			m_editVA.SetWindowText(_T("00000000"));
			MessageBox(_T("非法地址"));
		}
	}
	else if (FOA)
	{
		CString strFOA;
		m_editFOA.GetWindowTextW(strFOA);
		int nNum16;
		nNum16 = _tcstol(strFOA, NULL, 16);
		int nNum;
		nNum = FoaToRva(m_pBuff, nNum16);
		if (nNum)
		{
			strFOA.Format(_T("%08X"), nNum);
			m_editRVA.SetWindowTextW(strFOA);
			nNum = FoaToVa(m_pBuff, nNum16);
			strFOA.Format(_T("%08X"), nNum);
			m_editVA.SetWindowTextW(strFOA);
		}
		else
		{
			m_editFOA.SetWindowText(_T("00000000"));
			m_editRVA.SetWindowText(_T("00000000"));
			m_editVA.SetWindowText(_T("00000000"));
			MessageBox(_T("非法地址"));
		}
	}
}


void CBaseConversionCalculatorDlg::OnDropFiles(HDROP hDropInfo)
{
	// TODO: 在此添加消息处理程序代码和/或调用默认值
	ObtainFilePath(hDropInfo);
	OpenFile();

	CDialogEx::OnDropFiles(hDropInfo);
}
