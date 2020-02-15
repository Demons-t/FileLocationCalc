
// BaseConversionCalculatorDlg.h: 头文件
//

#pragma once


// CBaseConversionCalculatorDlg 对话框
class CBaseConversionCalculatorDlg : public CDialogEx
{
// 构造
public:
	CBaseConversionCalculatorDlg(CWnd* pParent = nullptr);	// 标准构造函数

// 对话框数据
#ifdef AFX_DESIGN_TIME
	enum { IDD = IDD_BASECONVERSIONCALCULATOR_DIALOG };
#endif

	protected:
	virtual void DoDataExchange(CDataExchange* pDX);	// DDX/DDV 支持
	DWORD RvaToFoa(char* pFileData, DWORD dwRva);
	DWORD RvaToVa(char* pFileData, DWORD dwRva);
	DWORD VaToRva(char* pFileData, DWORD dwVa);
	DWORD VaToFoa(char* pFileData, DWORD dwVa);
	DWORD FoaToRva(char* pFileData, DWORD dwFoa);
	DWORD FoaToVa(char* pFileData, DWORD dwFoa);
	void ObtainFilePath(HDROP hDropInfo);
	void OpenFile();

// 实现
protected:
	HICON m_hIcon;

	// 生成的消息映射函数
	virtual BOOL OnInitDialog();
	afx_msg void OnPaint();
	afx_msg HCURSOR OnQueryDragIcon();
	DECLARE_MESSAGE_MAP()

private:
	CString m_strPath;
	CEdit m_editVA;
	CEdit m_editRVA;
	CEdit m_editFOA;
	CEdit m_editFilePath;
	HANDLE m_hFile;
	char* m_pBuff;

public:
	afx_msg void VA();
	afx_msg void RVA();
	afx_msg void FOA();
	afx_msg void StratCalculate();
	afx_msg void OnDropFiles(HDROP hDropInfo);
};
