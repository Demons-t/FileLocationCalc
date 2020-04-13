.class public Lcom/example/dummydex/DummyApplication;
.super Landroid/app/Application;
.source "DummyApplication.java"


# static fields
.field private static final TAG:Ljava/lang/String; = "kai"


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 20
    invoke-direct {p0}, Landroid/app/Application;-><init>()V

    return-void
.end method

.method private replaceClassLoader(Ldalvik/system/DexClassLoader;)V
    .locals 11
    .param p1, "dexClassLoader"    # Ldalvik/system/DexClassLoader;

    .line 44
    :try_start_0
    const-string v0, "android.app.ActivityThread"

    invoke-static {v0}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v0

    .line 46
    .local v0, "clzActivityThead":Ljava/lang/Class;
    const-string v1, "currentActivityThread"

    const/4 v2, 0x0

    new-array v3, v2, [Ljava/lang/Class;

    invoke-virtual {v0, v1, v3}, Ljava/lang/Class;->getDeclaredMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v1

    .line 48
    .local v1, "metCurrentActivityThread":Ljava/lang/reflect/Method;
    const/4 v3, 0x0

    new-array v2, v2, [Ljava/lang/Object;

    invoke-virtual {v1, v3, v2}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    .line 51
    .local v2, "objActivityThead":Ljava/lang/Object;
    const-string v3, "mBoundApplication"

    invoke-virtual {v0, v3}, Ljava/lang/Class;->getDeclaredField(Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v3

    .line 52
    .local v3, "fiemBoundApplication":Ljava/lang/reflect/Field;
    const/4 v4, 0x1

    invoke-virtual {v3, v4}, Ljava/lang/reflect/Field;->setAccessible(Z)V

    .line 54
    invoke-virtual {v3, v2}, Ljava/lang/reflect/Field;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v5

    .line 57
    .local v5, "objBoundApplication":Ljava/lang/Object;
    const-string v6, "android.app.ActivityThread$AppBindData"

    invoke-static {v6}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v6

    .line 59
    .local v6, "clzAppBindData":Ljava/lang/Class;
    const-string v7, "info"

    invoke-virtual {v6, v7}, Ljava/lang/Class;->getDeclaredField(Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v7

    .line 60
    .local v7, "fieInfo":Ljava/lang/reflect/Field;
    invoke-virtual {v7, v4}, Ljava/lang/reflect/Field;->setAccessible(Z)V

    .line 62
    invoke-virtual {v7, v5}, Ljava/lang/reflect/Field;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v8

    .line 65
    .local v8, "objInfo":Ljava/lang/Object;
    const-string v9, "android.app.LoadedApk"

    invoke-static {v9}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v9

    .line 67
    .local v9, "clzLoadedApk":Ljava/lang/Class;
    const-string v10, "mClassLoader"

    invoke-virtual {v9, v10}, Ljava/lang/Class;->getDeclaredField(Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v10

    .line 69
    .local v10, "fiemClassLoader":Ljava/lang/reflect/Field;
    invoke-virtual {v10, v4}, Ljava/lang/reflect/Field;->setAccessible(Z)V

    .line 70
    invoke-virtual {v10, v8, p1}, Ljava/lang/reflect/Field;->set(Ljava/lang/Object;Ljava/lang/Object;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 73
    .end local v0    # "clzActivityThead":Ljava/lang/Class;
    .end local v1    # "metCurrentActivityThread":Ljava/lang/reflect/Method;
    .end local v2    # "objActivityThead":Ljava/lang/Object;
    .end local v3    # "fiemBoundApplication":Ljava/lang/reflect/Field;
    .end local v5    # "objBoundApplication":Ljava/lang/Object;
    .end local v6    # "clzAppBindData":Ljava/lang/Class;
    .end local v7    # "fieInfo":Ljava/lang/reflect/Field;
    .end local v8    # "objInfo":Ljava/lang/Object;
    .end local v9    # "clzLoadedApk":Ljava/lang/Class;
    .end local v10    # "fiemClassLoader":Ljava/lang/reflect/Field;
    goto :goto_0

    .line 71
    :catch_0
    move-exception v0

    .line 72
    .local v0, "e":Ljava/lang/Exception;
    invoke-virtual {v0}, Ljava/lang/Exception;->printStackTrace()V

    .line 74
    .end local v0    # "e":Ljava/lang/Exception;
    :goto_0
    return-void
.end method

.method private replaceClassLoader1(Ldalvik/system/DexClassLoader;)V
    .locals 11
    .param p1, "dexClassLoader"    # Ldalvik/system/DexClassLoader;

    .line 80
    :try_start_0
    const-string v0, "android.app.ActivityThread"

    invoke-static {v0}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v0

    .line 82
    .local v0, "clzActivityThead":Ljava/lang/Class;
    const-string v1, "currentActivityThread"

    const/4 v2, 0x0

    new-array v3, v2, [Ljava/lang/Class;

    invoke-virtual {v0, v1, v3}, Ljava/lang/Class;->getDeclaredMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v1

    .line 84
    .local v1, "metCurrentActivityThread":Ljava/lang/reflect/Method;
    const/4 v3, 0x0

    new-array v2, v2, [Ljava/lang/Object;

    invoke-virtual {v1, v3, v2}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    .line 87
    .local v2, "objActivityThead":Ljava/lang/Object;
    const-string v3, "mInitialApplication"

    invoke-virtual {v0, v3}, Ljava/lang/Class;->getDeclaredField(Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v3

    .line 88
    .local v3, "fiemInitialApplication":Ljava/lang/reflect/Field;
    const/4 v4, 0x1

    invoke-virtual {v3, v4}, Ljava/lang/reflect/Field;->setAccessible(Z)V

    .line 90
    invoke-virtual {v3, v2}, Ljava/lang/reflect/Field;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v5

    .line 93
    .local v5, "objInitialApplication":Ljava/lang/Object;
    const-string v6, "android.app.Application"

    invoke-static {v6}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v6

    .line 95
    .local v6, "clzApplication":Ljava/lang/Class;
    const-string v7, "mLoadedApk"

    invoke-virtual {v6, v7}, Ljava/lang/Class;->getDeclaredField(Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v7

    .line 96
    .local v7, "fiemLoadedApk":Ljava/lang/reflect/Field;
    invoke-virtual {v7, v4}, Ljava/lang/reflect/Field;->setAccessible(Z)V

    .line 98
    invoke-virtual {v7, v5}, Ljava/lang/reflect/Field;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v8

    .line 101
    .local v8, "objmLoadedApk":Ljava/lang/Object;
    const-string v9, "android.app.LoadedApk"

    invoke-static {v9}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v9

    .line 103
    .local v9, "clzLoadedApk":Ljava/lang/Class;
    const-string v10, "mClassLoader"

    invoke-virtual {v9, v10}, Ljava/lang/Class;->getDeclaredField(Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v10

    .line 105
    .local v10, "fiemClassLoader":Ljava/lang/reflect/Field;
    invoke-virtual {v10, v4}, Ljava/lang/reflect/Field;->setAccessible(Z)V

    .line 106
    invoke-virtual {v10, v8, p1}, Ljava/lang/reflect/Field;->set(Ljava/lang/Object;Ljava/lang/Object;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 109
    .end local v0    # "clzActivityThead":Ljava/lang/Class;
    .end local v1    # "metCurrentActivityThread":Ljava/lang/reflect/Method;
    .end local v2    # "objActivityThead":Ljava/lang/Object;
    .end local v3    # "fiemInitialApplication":Ljava/lang/reflect/Field;
    .end local v5    # "objInitialApplication":Ljava/lang/Object;
    .end local v6    # "clzApplication":Ljava/lang/Class;
    .end local v7    # "fiemLoadedApk":Ljava/lang/reflect/Field;
    .end local v8    # "objmLoadedApk":Ljava/lang/Object;
    .end local v9    # "clzLoadedApk":Ljava/lang/Class;
    .end local v10    # "fiemClassLoader":Ljava/lang/reflect/Field;
    goto :goto_0

    .line 107
    :catch_0
    move-exception v0

    .line 108
    .local v0, "e":Ljava/lang/Exception;
    invoke-virtual {v0}, Ljava/lang/Exception;->printStackTrace()V

    .line 110
    .end local v0    # "e":Ljava/lang/Exception;
    :goto_0
    return-void
.end method

.method private replaceClassLoader2(Ldalvik/system/DexClassLoader;)V
    .locals 9
    .param p1, "dexClassLoader"    # Ldalvik/system/DexClassLoader;

    .line 116
    :try_start_0
    const-string v0, "android.app.ActivityThread"

    invoke-static {v0}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v0

    .line 118
    .local v0, "clazzActivityThread":Ljava/lang/Class;
    const-string v1, "currentActivityThread"

    const/4 v2, 0x0

    new-array v3, v2, [Ljava/lang/Class;

    invoke-virtual {v0, v1, v3}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v1

    const/4 v3, 0x0

    new-array v2, v2, [Ljava/lang/Object;

    invoke-virtual {v1, v3, v2}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    .line 120
    .local v1, "currentActivityThread":Ljava/lang/Object;
    const-string v2, "mPackages"

    invoke-virtual {v0, v2}, Ljava/lang/Class;->getDeclaredField(Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v2

    .line 121
    .local v2, "fieldmPackages":Ljava/lang/reflect/Field;
    const/4 v3, 0x1

    invoke-virtual {v2, v3}, Ljava/lang/reflect/Field;->setAccessible(Z)V

    .line 125
    invoke-virtual {v2, v1}, Ljava/lang/reflect/Field;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/util/Map;

    .line 127
    .local v4, "mPackages":Ljava/util/Map;
    invoke-virtual {p0}, Lcom/example/dummydex/DummyApplication;->getPackageName()Ljava/lang/String;

    move-result-object v5

    .line 129
    .local v5, "packageName":Ljava/lang/String;
    invoke-interface {v4, v5}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/lang/ref/WeakReference;

    .line 133
    .local v6, "ref":Ljava/lang/ref/WeakReference;
    const-string v7, "android.app.LoadedApk"

    invoke-static {v7}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v7

    .line 135
    .local v7, "clazzLoadedApk":Ljava/lang/Class;
    const-string v8, "mClassLoader"

    invoke-virtual {v7, v8}, Ljava/lang/Class;->getDeclaredField(Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v8

    .line 136
    .local v8, "fieldmClassLoader":Ljava/lang/reflect/Field;
    invoke-virtual {v8, v3}, Ljava/lang/reflect/Field;->setAccessible(Z)V

    .line 138
    invoke-virtual {v6}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v3

    invoke-virtual {v8, v3, p1}, Ljava/lang/reflect/Field;->set(Ljava/lang/Object;Ljava/lang/Object;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 141
    .end local v0    # "clazzActivityThread":Ljava/lang/Class;
    .end local v1    # "currentActivityThread":Ljava/lang/Object;
    .end local v2    # "fieldmPackages":Ljava/lang/reflect/Field;
    .end local v4    # "mPackages":Ljava/util/Map;
    .end local v5    # "packageName":Ljava/lang/String;
    .end local v6    # "ref":Ljava/lang/ref/WeakReference;
    .end local v7    # "clazzLoadedApk":Ljava/lang/Class;
    .end local v8    # "fieldmClassLoader":Ljava/lang/reflect/Field;
    goto :goto_0

    .line 139
    :catch_0
    move-exception v0

    .line 140
    .local v0, "e":Ljava/lang/Exception;
    invoke-virtual {v0}, Ljava/lang/Exception;->printStackTrace()V

    .line 142
    .end local v0    # "e":Ljava/lang/Exception;
    :goto_0
    return-void
.end method


# virtual methods
.method protected attachBaseContext(Landroid/content/Context;)V
    .locals 2
    .param p1, "base"    # Landroid/content/Context;

    .line 26
    invoke-super {p0, p1}, Landroid/app/Application;->attachBaseContext(Landroid/content/Context;)V

    .line 28
    const-string v0, "src.dex"

    invoke-virtual {p0, v0}, Lcom/example/dummydex/DummyApplication;->copyDex(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 30
    .local v0, "path":Ljava/lang/String;
    invoke-virtual {p0, v0}, Lcom/example/dummydex/DummyApplication;->getLoder(Ljava/lang/String;)Ldalvik/system/DexClassLoader;

    move-result-object v1

    .line 32
    .local v1, "dexClassLoader":Ldalvik/system/DexClassLoader;
    invoke-direct {p0, v1}, Lcom/example/dummydex/DummyApplication;->replaceClassLoader(Ldalvik/system/DexClassLoader;)V

    .line 33
    return-void
.end method

.method public copyDex(Ljava/lang/String;)Ljava/lang/String;
    .locals 9
    .param p1, "dexName"    # Ljava/lang/String;

    .line 156
    invoke-virtual {p0}, Lcom/example/dummydex/DummyApplication;->getAssets()Landroid/content/res/AssetManager;

    move-result-object v0

    .line 158
    .local v0, "as":Landroid/content/res/AssetManager;
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p0}, Lcom/example/dummydex/DummyApplication;->getFilesDir()Ljava/io/File;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    sget-object v2, Ljava/io/File;->separator:Ljava/lang/String;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    .line 159
    .local v1, "path":Ljava/lang/String;
    const-string v2, "kai"

    invoke-static {v2, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 162
    :try_start_0
    new-instance v2, Ljava/io/FileOutputStream;

    invoke-direct {v2, v1}, Ljava/io/FileOutputStream;-><init>(Ljava/lang/String;)V

    .line 164
    .local v2, "out":Ljava/io/FileOutputStream;
    invoke-virtual {v0, p1}, Landroid/content/res/AssetManager;->open(Ljava/lang/String;)Ljava/io/InputStream;

    move-result-object v3

    .line 166
    .local v3, "is":Ljava/io/InputStream;
    const/16 v4, 0x400

    new-array v4, v4, [B

    .line 167
    .local v4, "buffer":[B
    const/4 v5, 0x0

    move v6, v5

    .line 168
    .local v6, "len":I
    :goto_0
    invoke-virtual {v3, v4}, Ljava/io/InputStream;->read([B)I

    move-result v7

    move v6, v7

    const/4 v8, -0x1

    if-eq v7, v8, :cond_0

    .line 170
    invoke-virtual {v2, v4, v5, v6}, Ljava/io/FileOutputStream;->write([BII)V
    :try_end_0
    .catch Ljava/io/FileNotFoundException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    .line 168
    .end local v2    # "out":Ljava/io/FileOutputStream;
    .end local v3    # "is":Ljava/io/InputStream;
    .end local v4    # "buffer":[B
    .end local v6    # "len":I
    :cond_0
    goto :goto_1

    .line 174
    :catch_0
    move-exception v2

    .line 175
    .local v2, "e":Ljava/io/IOException;
    invoke-virtual {v2}, Ljava/io/IOException;->printStackTrace()V

    goto :goto_2

    .line 172
    .end local v2    # "e":Ljava/io/IOException;
    :catch_1
    move-exception v2

    .line 173
    .local v2, "e":Ljava/io/FileNotFoundException;
    invoke-virtual {v2}, Ljava/io/FileNotFoundException;->printStackTrace()V

    .line 176
    .end local v2    # "e":Ljava/io/FileNotFoundException;
    :goto_1
    nop

    .line 177
    :goto_2
    return-object v1
.end method

.method public getLoder(Ljava/lang/String;)Ldalvik/system/DexClassLoader;
    .locals 4
    .param p1, "dexPath"    # Ljava/lang/String;

    .line 146
    new-instance v0, Ldalvik/system/DexClassLoader;

    .line 148
    invoke-virtual {p0}, Lcom/example/dummydex/DummyApplication;->getCacheDir()Ljava/io/File;

    move-result-object v1

    invoke-virtual {v1}, Ljava/io/File;->toString()Ljava/lang/String;

    move-result-object v1

    .line 150
    invoke-virtual {p0}, Lcom/example/dummydex/DummyApplication;->getClassLoader()Ljava/lang/ClassLoader;

    move-result-object v2

    const/4 v3, 0x0

    invoke-direct {v0, p1, v1, v3, v2}, Ldalvik/system/DexClassLoader;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/ClassLoader;)V

    .line 151
    .local v0, "dexClassLoader":Ldalvik/system/DexClassLoader;
    return-object v0
.end method

.method public onCreate()V
    .locals 0

    .line 37
    invoke-super {p0}, Landroid/app/Application;->onCreate()V

    .line 38
    return-void
.end method
