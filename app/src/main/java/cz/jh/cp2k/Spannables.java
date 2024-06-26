package cz.jh.cp2k;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;

// the last colorization within the function is the most important (will be applied as the last)
// rule: the first are the least important colorizations, overriden by the last ones

public class Spannables extends MainActivity {

    public static Spannable colorized_numbers(final String text) {
        SpannableString spannable = new SpannableString(text);
        // when the white background span is applied, the text selection is not visible (only the non-spanned parts)
        Integer argb_back = Color.WHITE;

        String[] words_red = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "+", "-"};
        Integer argb_red = Color.RED;
        for (String word : words_red) {
            int substringStart=0;
            int start;
            while((start=text.indexOf(word,substringStart))>=0){
                spannable.setSpan(
                        new ForegroundColorSpan(argb_red),start,start+word.length(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                );
//                spannable.setSpan(
//                        new BackgroundColorSpan(argb_back),start,start+word.length(),
//                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
//                );
                substringStart = start+word.length();
            }
        }

        return spannable;
    }

    public static Spannable colorized_bash(final String text) {
        SpannableString spannable = new SpannableString(text);
        Integer argb_back = Color.WHITE;

        String[] words_green = {"cd ", "export ", "acpid ", "adjtimex ", "ar ", "arch ", "arp ", "arping ", "ash ", "awk ", "basename ", "bc ", "blkdiscard ", "blockdev ", "brctl ", "bunzip2 ", "bzcat ", "bzip2 ", "cal ", "cat ", "chgrp ", "chmod ", "chown ", "chroot ", "chvt ", "clear ", "cmp ", "cp ", "cpio ", "cttyhack ", "cut ", "date ", "dc ", "dd ", "deallocvt ", "depmod ", "devmem ", "df ", "diff ", "dirname ", "dmesg ", "dnsdomainname ", "dos2unix ", "du ", "dumpkmap ", "dumpleases ", "echo ", "egrep ", "env ", "expand ", "expr ", "factor ", "fallocate ", "false ", "fatattr ", "fgrep ", "find ", "fold ", "free ", "freeramdisk ", "fsfreeze ", "fstrim ", "ftpget ", "ftpput ", "getopt ", "getty ", "grep ", "groups ", "gunzip ", "gzip ", "halt ", "head ", "hexdump ", "hostid ", "hostname ", "httpd ", "hwclock ", "i2cdetect ", "i2cdump ", "i2cget ", "i2cset ", "id ", "ifconfig ", "ifdown ", "ifup ", "init ", "insmod ", "ionice ", "ip ", "ipcalc ", "ipneigh ", "kill ", "killall ", "klogd ", "last ", "less ", "link ", "linux32 ", "linux64 ", "linuxrc ", "ln ", "loadfont ", "loadkmap ", "logger ", "login ", "logname ", "logread ", "losetup ", "ls ", "lsmod ", "lsscsi ", "lzcat ", "lzma ", "lzop ", "md5sum ", "mdev ", "microcom ", "mkdir ", "mkdosfs ", "mke2fs ", "mkfifo ", "mknod ", "mkpasswd ", "mkswap ", "mktemp ", "modinfo ", "modprobe ", "more ", "mount ", "mt ", "mv ", "nameif ", "nc ", "netstat ", "nl ", "nologin ", "nproc ", "nsenter ", "nslookup ", "nuke ", "od ", "openvt ", "partprobe ", "paste ", "patch ", "pidof ", "ping ", "ping6 ", "pivot_root ", "poweroff ", "printf ", "ps ", "pwd ", "rdate ", "readlink ", "realpath ", "reboot ", "renice ", "reset ", "resume ", "rev ", "rm ", "rmdir ", "rmmod ", "route ", "rpm ", "rpm2cpio ", "run-init ", "run-parts ", "sed ", "seq ", "setkeycodes ", "setpriv ", "setsid ", "sh ", "sha1sum ", "sha256sum ", "sha512sum ", "shred ", "shuf ", "sleep ", "sort ", "ssl_client ", "start-stop-daemon ", "stat ", "strings ", "stty ", "svc ", "svok ", "swapoff ", "swapon ", "switch_root ", "sync ", "sysctl ", "syslogd ", "tac ", "tail ", "tar ", "taskset ", "tee ", "telnet ", "test ", "tftp ", "time ", "timeout ", "top ", "touch ", "tr ", "traceroute ", "traceroute6 ", "true ", "truncate ", "tty ", "ubirename ", "udhcpc ", "udhcpd ", "uevent ", "umount ", "uname ", "uncompress ", "unexpand ", "uniq ", "unix2dos ", "unlink ", "unlzma ", "unshare ", "unxz ", "unzip ", "uptime ", "usleep ", "uudecode ", "uuencode ", "vconfig ", "vi ", "w ", "watch ", "watchdog ", "wc ", "wget ", "which ", "who ", "whoami ", "xargs ", "xxd ", "xz ", "xzcat ", "yes ", "zcat ", "obabel ", "cp2k ", "dbm_miniapp ", "dumpdcd ", "graph ", "grid_miniapp ", "xbbc ", "xbvm ", "xyz2dcd ", "toybox "};
        Integer argb_green = Color.parseColor("#66BB6A");
        for (String word : words_green) {
            int substringStart=0;
            int start;
            while((start=text.indexOf(word,substringStart))>=0){
                spannable.setSpan(
                        new ForegroundColorSpan(argb_green),start,start+word.length(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                );
//                spannable.setSpan(
//                        new BackgroundColorSpan(argb_back),start,start+word.length(),
//                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
//                );
                substringStart = start+word.length();
            }
        }

        String[] words_red = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "+", "-"};
        Integer argb_red = Color.RED;
        for (String word : words_red) {
            int substringStart=0;
            int start;
            while((start=text.indexOf(word,substringStart))>=0){
                spannable.setSpan(
                        new ForegroundColorSpan(argb_red),start,start+word.length(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                );
//                spannable.setSpan(
//                        new BackgroundColorSpan(argb_back),start,start+word.length(),
//                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
//                );
                substringStart = start+word.length();
            }
        }

        String[] words_comment = {"#", "!"};
        Integer argb_comment = Color.parseColor("#058a47");
        for (String word : words_comment) {
            int substringStart = 0;
            int start;
            while ((start = text.indexOf(word, substringStart)) >= 0) {
                int endOfLine = text.indexOf("\n",start);
                // endOfLine = -1 in the last line, since there is no more line break until the ond of the file
                if (endOfLine != -1) {
                    spannable.setSpan(
                            new ForegroundColorSpan(argb_comment), start, endOfLine,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    );
//                    spannable.setSpan(
//                            new BackgroundColorSpan(argb_back),start,endOfLine,
//                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
//                    );
                    substringStart = endOfLine;
                } else {
                    spannable.setSpan(
                            // in the last line, only the "#" character would be colorized by green, the following characters would be either black or colorized by previous
//                            new ForegroundColorSpan(argb_comment), start, start+word.length(),
                            // text.length() is the position of the end of whole text, better (it works as should)
                            new ForegroundColorSpan(argb_comment), start, text.length(),
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    );
//                    spannable.setSpan(
//                            new BackgroundColorSpan(argb_back),start,text.length(),
//                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
//                    );
                    substringStart = start+word.length();
                }
            }
        }

        return spannable;
    }

    public static Spannable colorized_x11basic(final String text) {
        SpannableString spannable = new SpannableString(text);
        Integer argb_back = Color.WHITE;

        String[] words_basic = {"ABS(", "ABSOLUTE", "ACOS(", "ACOSH(", "ADD", "ADD(", "AFTER", "ALERT", "AND", "AND(", "ANDROID?", "ARG(", "ARID$(", "ARIE$(", "ARRAYCOPY", "ARRAYFILL", "ARRPTR(", "ASC(", "ASIN(", "ASINH(", "AT(", "ATAN(", "ATAN2(", "ATANH(", "ATN(", "BCHG(", "BCLR(", "BEEP", "BELL", "BGET", "BIN$(", "BLOAD", "BMOVE", "BOTTOMW", "BOUNDARY", "BOX", "BPUT", "BREAK", "BSAVE", "BSET(", "BTST(", "BWTD$(", "BWTE$(", "BYTE(", "CALL", "CALL(", "CALL$(", "CALLD(", "CARD(", "CASE", "CBRT(", "CEIL(", "CGI", "CHAIN", "CHDIR", "CHMOD", "CHR$(", "CINT(", "CIRCLE", "CLEAR", "CLEARW", "CLIP", "CLOSE", "CLOSEW", "CLR", "CLS", "COLOR", "COLOR(", "COLOR_RGB(", "COLS", "COMBIN(", "COMPRESS$(", "CONJ(", "CONNECT", "CONT", "CONTINUE", "COPY_AREA", "COPYAREA", "COS(", "COSH(", "CRC(", "CRSCOL", "CRSLIN", "CTIMER", "CURVE", "CVA(", "CVD(", "CVF(", "CVI(", "CVL(", "CVS(", "DATA", "DATE$", "DEC", "DECLOSE$(", "DECRYPT$(", "DEFAULT", "DEFFILL", "DEFFN", "DEFLINE", "DEFMARK", "DEFMOUSE", "DEFTEXT", "DEG(", "DELAY", "DET(", "DEVICE(", "DIM", "DIM?", "DIM?(", "DIR$(", "DIV", "DIV(", "DO", "DOWNTO", "DPEEK(", "DPOKE", "DRAW", "DUMP", "ECHO", "EDIT", "ELLIPSE", "ELSE", "ELSE IF", "ENCLOSE$(", "ENCRYPT$(", "END", "ENDFUNCTION", "ENDIF", "ENDPROCEDURE", "ENDSELECT", "ENV$(", "EOF(", "EQV", "ERASE", "ERR", "ERR$(", "ERROR", "EVAL", "EVAL(", "EVEN(", "EVENT", "EVENT?(", "EVERY", "EXEC", "EXEC(", "EXIST(", "EXIT", "EXIT IF", "EXP(", "EXPM1(", "FACT(", "FALSE", "FATAL", "FFT", "FFT(", "FIB(", "FILEEVENT$", "FILESELECT", "FILL", "FIT", "FIT_LINEAR", "FIT_POLY", "FIX(", "FLOOR(", "FLUSH", "FOR", "FORK(", "FORMINPUT AS", "FORM_ALERT(", "FORM_CENTER(", "FORM_DIAL(", "FORM_DO(", "FRAC(", "FREE", "FREE(", "FREEFILE(", "FSEL_INPUT(", "FSFIRST$(", "FSNEXT$(", "FULLW", "FUNCTION", "GAMMA(", "GASDEV", "GASDEV(", "GCD(", "GET", "GET_COLOR(", "GET_GEOMETRY", "GET_LOCATION", "GET_SCREENSIZE", "GLOB(", "GOLOR_RGB(", "GOSUB", "GOSUBABBREV", "GOTO", "GPRINT", "GPS", "GPS?", "GPS_ALT", "GPS_LAT", "GPS_LON", "GRAPHMODE", "GRAY(", "HASH$(", "HELP", "HEX$(", "HIDEK", "HIDEM", "HOME", "HYPOT(", "IDE", "IF", "IMAG(", "IMP", "INC", "INFOW", "INKEY$", "INLINE$(", "INODE(", "INP(", "INP?(", "INP%(", "INP&(", "INPUT", "INPUT$(", "INSTR(", "INT(", "INV(", "INVERT(", "IOCTL(", "JULDATE$(", "JULIAN(", "KEYEVENT", "KILL", "LCM(", "LEFT$(", "LEFTOF$(", "LEN(", "LET", "LGAMMA(", "LINE", "LINEINPUT", "LINEINPUT$(", "LINK", "LIST", "LISTSELECT(", "LN(", "LOAD", "LOC(", "LOCAL", "LOCATE", "LOF(", "LOG(", "LOG10(", "LOG1P(", "LOGB(", "LOOP", "LOWER$(", "LPEEK(", "LPOKE", "LTEXT", "LTEXTLEN(", "LUCNUM(", "MALLOC(", "MAP", "MAX(", "MENU", "MENUDEF", "MENUKILL", "MENUSET", "MERGE", "MFREE", "MFREE(", "MID$(", "MIN(", "MKA$(", "MKD$(", "MKDIR", "MKF$(", "MKI$(", "MKL$(", "MKS$(", "MOD", "MOD(", "MODE(", "MOTIONEVENT", "MOUSE", "MOUSEEVENT", "MOUSEK", "MOUSES", "MOUSEX", "MOUSEY", "MOVEW", "MSHRINK(", "MSYNC", "MTFD$(", "MTFE$(", "MUL", "MUL(", "NAND", "NEW", "NEXT", "NEXTPRIME(", "NLINK(", "NOOP", "NOP", "NOR", "NOROOTWINDOW", "NOT", "OBJ_DRAW(", "OBJC_ADD", "OBJC_DELETE", "OBJC_DRAW(", "OBJC_FIND(", "OBJC_OFFSET(", "OCT$(", "ODD(", "ON*GOSUB", "ON*GOTO", "ONBREAK", "ONERROR", "ONERRORGOSUB", "OPEN", "OPENW", "OR", "OR(", "OUT", "OUT?(", "PARAM$(", "PAUSE", "PBOX", "PC", "PCIRCLE", "PEEK(", "PELLIPSE", "PI", "PIPE", "PLAYSOUND", "PLAYSOUNDFILE", "PLIST", "PLOT", "PNGDECODE$(", "PNGENCODE$(", "POINT(", "POKE", "POLYFILL", "POLYLINE", "POLYMARK", "POWM(", "PRBOX", "PRED(", "PRG$(", "PRIMORIAL(", "PRINT", "PRINT AT(", "PRINT COLOR(", "PRINT SPC(", "PRINT TAB(", "PRINT TAB( SPC(", "PRINT USING", "PROCEDURE", "PROGRAM", "PTST(", "PUT", "PUT_BITMAP", "PUTBACK", "QUIT", "RAD(", "RADIX$(", "RAND(", "RANDOM(", "RANDOMIZE", "RBOX", "READ", "REAL(", "REALLOC", "REALLOC(", "RECEIVE", "RELSEEK", "REM", "REMABBREV", "RENAME", "REPEAT", "REPLACE$(", "RESTORE", "RESUME", "RETURN", "REVERSE$(", "RIGHT$(", "RIGHTOF$(", "RINSTR(", "RLD$(", "RLE$(", "RMDIR", "RND(", "ROL(", "ROOT(", "ROOTWINDOW", "ROR(", "ROUND(", "ROWS", "RPM", "RSRC_FREE", "RSRC_LOAD", "RUN", "SAVE", "SAVESCREEN", "SAVEWINDOW", "SCOPE", "SCREEN", "SEEK", "SELECT", "SEND", "SENSOR", "SENSOR(", "SENSOR?", "SETENV", "SETFONT", "SETMOUSE", "SGET", "SGN(", "SHELL", "SHL(", "SHM_ATTACH(", "SHM_DETACH", "SHM_FREE", "SHM_MALLOC(", "SHOWK", "SHOWM", "SHOWPAGE", "SHR(", "SIGN$(", "SIN(", "SINH(", "SIZE(", "SIZEW", "SOLVE", "SOLVE(", "SORT", "SOUND", "SP", "SPACE$(", "SPAWN", "SPC(", "SPEAK", "SPLIT", "SPUT", "SQR(", "SQRT(", "SRAND(", "STEP", "STIMER", "STOP", "STR$(", "STRING$(", "SUB", "SUB(", "SUCC(", "SWAP", "SWAP(", "SYM_ADR(", "SYSTEM", "SYSTEM$(", "TAB(", "TALLY(", "TAN(", "TANH(", "TERMINALNAME$", "TERMINALNAME$(", "TEXT", "TIME$", "TIMER", "TITLEW", "TO", "TOPW", "TOUCH", "TRACE", "TRACE$", "TRIM$(", "TROFF", "TRON", "TRUE", "TRUNC(", "TT?", "TYP?(", "UBOUND(", "UCASE$(", "UNCOMPRESS$(", "UNIX", "UNIX?", "UNIXDATE$(", "UNIXTIME$(", "UNLINK", "UNMAP", "UNTIL", "UPPER$(", "USEWINDOW", "USING$(", "VAL(", "VAL?(", "VAR", "VARIAT(", "VARPTR(", "VERSION", "VOID", "ABBREV", "VRFY(", "VSYNC", "VT100", "WATCH", "WAVE", "WEND", "WHILE", "WIN32?", "WORD(", "WORD$(", "WORT_SEP", "WORT_SEP(", "XLOAD", "XOR", "XOR(", "XRUN", "XTRIM$(", "abs(", "absolute", "acos(", "acosh(", "add", "add(", "after", "alert", "and", "and(", "android?", "arg(", "arid$(", "arie$(", "arraycopy", "arrayfill", "arrptr(", "asc(", "asin(", "asinh(", "at(", "atan(", "atan2(", "atanh(", "atn(", "bchg(", "bclr(", "beep", "bell", "bget", "bin$(", "bload", "bmove", "bottomw", "boundary", "box", "bput", "break", "bsave", "bset(", "btst(", "bwtd$(", "bwte$(", "byte(", "call", "call(", "call$(", "calld(", "card(", "case", "cbrt(", "ceil(", "cgi", "chain", "chdir", "chmod", "chr$(", "cint(", "circle", "clear", "clearw", "clip", "close", "closew", "clr", "cls", "color", "color(", "color_rgb(", "cols", "combin(", "compress$(", "conj(", "connect", "cont", "continue", "copy_area", "copyarea", "cos(", "cosh(", "crc(", "crscol", "crslin", "ctimer", "curve", "cva(", "cvd(", "cvf(", "cvi(", "cvl(", "cvs(", "data", "date$", "dec", "declose$(", "decrypt$(", "default", "deffill", "deffn", "defline", "defmark", "defmouse", "deftext", "deg(", "delay", "det(", "device(", "dim", "dim?", "dim?(", "dir$(", "div", "div(", "do", "downto", "dpeek(", "dpoke", "draw", "dump", "echo", "edit", "ellipse", "else", "else if", "enclose$(", "encrypt$(", "end", "endfunction", "endif", "endprocedure", "endselect", "env$(", "eof(", "eqv", "erase", "err", "err$(", "error", "eval", "eval(", "even(", "event", "event?(", "every", "exec", "exec(", "exist(", "exit", "exit if", "exp(", "expm1(", "fact(", "false", "fatal", "fft", "fft(", "fib(", "fileevent$", "fileselect", "fill", "fit", "fit_linear", "fit_poly", "fix(", "floor(", "flush", "for", "fork(", "forminput as", "form_alert(", "form_center(", "form_dial(", "form_do(", "frac(", "free", "free(", "freefile(", "fsel_input(", "fsfirst$(", "fsnext$(", "fullw", "function", "gamma(", "gasdev", "gasdev(", "gcd(", "get", "get_color(", "get_geometry", "get_location", "get_screensize", "glob(", "golor_rgb(", "gosub", "gosubabbrev", "goto", "gprint", "gps", "gps?", "gps_alt", "gps_lat", "gps_lon", "graphmode", "gray(", "hash$(", "help", "hex$(", "hidek", "hidem", "home", "hypot(", "ide", "if", "imag(", "imp", "inc", "infow", "inkey$", "inline$(", "inode(", "inp(", "inp?(", "inp%(", "inp&(", "input", "input$(", "instr(", "int(", "inv(", "invert(", "ioctl(", "juldate$(", "julian(", "keyevent", "kill", "lcm(", "left$(", "leftof$(", "len(", "let", "lgamma(", "line", "lineinput", "lineinput$(", "link", "list", "listselect(", "ln(", "load", "loc(", "local", "locate", "lof(", "log(", "log10(", "log1p(", "logb(", "loop", "lower$(", "lpeek(", "lpoke", "ltext", "ltextlen(", "lucnum(", "malloc(", "map", "max(", "menu", "menudef", "menukill", "menuset", "merge", "mfree", "mfree(", "mid$(", "min(", "mka$(", "mkd$(", "mkdir", "mkf$(", "mki$(", "mkl$(", "mks$(", "mod", "mod(", "mode(", "motionevent", "mouse", "mouseevent", "mousek", "mouses", "mousex", "mousey", "movew", "mshrink(", "msync", "mtfd$(", "mtfe$(", "mul", "mul(", "nand", "new", "next", "nextprime(", "nlink(", "noop", "nop", "nor", "norootwindow", "not", "obj_draw(", "objc_add", "objc_delete", "objc_draw(", "objc_find(", "objc_offset(", "oct$(", "odd(", "on*gosub", "on*goto", "onbreak", "onerror", "onerrorgosub", "open", "openw", "or", "or(", "out", "out?(", "param$(", "pause", "pbox", "pc", "pcircle", "peek(", "pellipse", "pi", "pipe", "playsound", "playsoundfile", "plist", "plot", "pngdecode$(", "pngencode$(", "point(", "poke", "polyfill", "polyline", "polymark", "powm(", "prbox", "pred(", "prg$(", "primorial(", "print", "print at(", "print color(", "print spc(", "print tab(", "print tab( spc(", "print using", "procedure", "program", "ptst(", "put", "put_bitmap", "putback", "quit", "rad(", "radix$(", "rand(", "random(", "randomize", "rbox", "read", "real(", "realloc", "realloc(", "receive", "relseek", "rem", "remabbrev", "rename", "repeat", "replace$(", "restore", "resume", "return", "reverse$(", "right$(", "rightof$(", "rinstr(", "rld$(", "rle$(", "rmdir", "rnd(", "rol(", "root(", "rootwindow", "ror(", "round(", "rows", "rpm", "rsrc_free", "rsrc_load", "run", "save", "savescreen", "savewindow", "scope", "screen", "seek", "select", "send", "sensor", "sensor(", "sensor?", "setenv", "setfont", "setmouse", "sget", "sgn(", "shell", "shl(", "shm_attach(", "shm_detach", "shm_free", "shm_malloc(", "showk", "showm", "showpage", "shr(", "sign$(", "sin(", "sinh(", "size(", "sizew", "solve", "solve(", "sort", "sound", "sp", "space$(", "spawn", "spc(", "speak", "split", "sput", "sqr(", "sqrt(", "srand(", "step", "stimer", "stop", "str$(", "string$(", "sub", "sub(", "succ(", "swap", "swap(", "sym_adr(", "system", "system$(", "tab(", "tally(", "tan(", "tanh(", "terminalname$", "terminalname$(", "text", "time$", "timer", "titlew", "to", "topw", "touch", "trace", "trace$", "trim$(", "troff", "tron", "true", "trunc(", "tt?", "typ?(", "ubound(", "ucase$(", "uncompress$(", "unix", "unix?", "unixdate$(", "unixtime$(", "unlink", "unmap", "until", "upper$(", "usewindow", "using$(", "val(", "val?(", "var", "variat(", "varptr(", "version", "void", "abbrev", "vrfy(", "vsync", "vt100", "watch", "wave", "wend", "while", "win32?", "word(", "word$(", "wort_sep", "wort_sep(", "xload", "xor", "xor(", "xrun", "xtrim$(", "(", ")"};
        Integer argb_basic = Color.parseColor("#A52A2A");
        Integer argb_basic_back = Color.parseColor("#FFE797");
        for (String word : words_basic) {
            int substringStart=0;
            int start;
            while((start=text.indexOf(word,substringStart))>=0){
                spannable.setSpan(
                        new ForegroundColorSpan(argb_basic),start,start+word.length(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                );
                spannable.setSpan(
                        new BackgroundColorSpan(argb_basic_back),start,start+word.length(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                );
                substringStart = start+word.length();
            }
        }

        String[] words_red = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "+", "-"};
        Integer argb_red = Color.RED;
        for (String word : words_red) {
            int substringStart=0;
            int start;
            while((start=text.indexOf(word,substringStart))>=0){
                spannable.setSpan(
                        new ForegroundColorSpan(argb_red),start,start+word.length(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                );
//                spannable.setSpan(
//                        new BackgroundColorSpan(argb_back),start,start+word.length(),
//                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
//                );
                substringStart = start+word.length();
            }
        }

        String[] words_comment = {"#", "REM", "!"};
        Integer argb_comment = Color.parseColor("#058a47");
        for (String word : words_comment) {
            int substringStart = 0;
            int start;
            while ((start = text.indexOf(word, substringStart)) >= 0) {
                int endOfLine = text.indexOf("\n",start);
                // endOfLine = -1 in the last line, since there is no more line break until the ond of the file
                if (endOfLine != -1) {
                    spannable.setSpan(
                            new ForegroundColorSpan(argb_comment), start, endOfLine,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    );
//                    spannable.setSpan(
//                            new BackgroundColorSpan(argb_back),start,endOfLine,
//                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
//                    );
                    substringStart = endOfLine;
                } else {
                    spannable.setSpan(
                            // in the last line, only the "#" character would be colorized by green, the following characters would be either black or colorized by previous
//                            new ForegroundColorSpan(argb_comment), start, start+word.length(),
                            // text.length() is the position of the end of whole text, better (it works as should)
                            new ForegroundColorSpan(argb_comment), start, text.length(),
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    );
//                    spannable.setSpan(
//                            new BackgroundColorSpan(argb_back),start,text.length(),
//                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
//                    );
                    substringStart = start+word.length();
                }
            }
        }

        return spannable;
    }

}
