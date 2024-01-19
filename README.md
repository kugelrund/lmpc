# LMPC

LMPC is the Little Movie Processing Centre.
LMPC helps you to handle LMP, DMO, DEM, QWD, DM2, and DM3 files.
This is a fork of the original project by Uwe Girlich (https://sourceforge.net/projects/lmpc/).

## Compilation

### Prerequisites

Autotools is used for building LMPC.
Further requirements are C and C++ compilers and the tools `lex`, `yacc` and `docbook2html` (for generating the documentation).
On Ubuntu for example, they may be installed with:

```bash
$ sudo apt install autotools-dev bison flex docbook-utils
```

### Compilation

From the top-level directory, run these commands in order:

```bash
$ autoreconf -ivf
$ mkdir build
$ cd build
$ ../configure
$ make
```

If you would like to cross-compile for Windows, you may want to use `../configure --host=i686-w64-mingw32` instead.
