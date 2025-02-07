# CP2K Android app
based on CP2K code

Description & Use:
CP2K is a well-known multipurpose electronic structure package capable of performing wide variety of computational tasks at broad choice of level of theory. The contained binaries are OpenMP parallelized, thus the users can set number of threads for running the job. The app is working completely offline, does not collect, transmit, or distribute any personally identifiable information of the app users.

IMPORTANT !!!
Although this app is composed of open-source codes and resources, licenses for some components require the users to cite the original references when publishing the results. Please check all the licensing information under the buttons 'License' and 'About the app'.
All the users of the CP2K app comply by downloading, installing and using it with all the licensing conditions of the individual software components and take the responsibility for keeping them. 

License: The CP2K app repository (except from the binary content in /app/src/main/jniLibs/) itself is covered by GPL3 license. However, the included third-party software as well as the corresponding documentation retains the original licenses - please see the individual entries below. 
App source code: https://github.com/alanliska/CP2K

Contact:
Compilation of the source code for Android as well as the Android app development was done by Alan Liška (alan.liska@jh-inst.cas.cz) and Veronika Růžičková (sucha.ver@gmail.com), J. Heyrovský Institute of Physical Chemistry of the CAS, v.v.i., Dolejškova 3/2155, 182 23 Praha 8, Czech Republic.
Website: http://www.jh-inst.cas.cz/~liska/MobileChemistry.htm

List of used third-party software:
ACPDFVIEW, ANDROID SHELL, BLAS, CP2K, FFTW, GRAPHVIEW, LAPACK, LIBINT, LIBXC, MOLCANVAS, OPENBABEL, OPSIN, X11-BASIC.  

More info on licenses & references - please refer to the licensing information inside of the app.

## Compiling of the code

Because of the GitHub filesize limit (<= 100 MB), the binary libcp2k.so was packed as a ZIP file. Therefore, before the compilation, it is necessary to unzip the file and remove the ZIP. 

```bash
$ cd app/src/main/jniLibs/armeabi-v7a
$ unzip libcp2k.zip
$ rm libcp2k.zip
```

==================================================================================================

 * ACPDFVIEW
 Author: Bhuvaneshw (Github)
 Source code: https://github.com/Bhuvaneshw/acpdfview
 License: GNU GPL-3.0

 * ANDROID SHELL
 Author: Jorrit "Chainfire" Jongma (JRummy Apps Inc.)
 Source code: https://github.com/aa668086/android-shell-master
 License: Apache License, Version 2.0
 
 * BLAS
 Source code: https://netlib.org/blas/
 License: freely-available software package

 * CP2K
 Authors: CP2K developers group (as stated in GitHub repository) including Joost VandeVondele, Tiziano Müller, Ole Schütt, Matthias Krack, Jürg Hutter and many others
 Ref.: CP2K, A. General Program to Perform Molecular Dynamics Simulations. CP2k developers group under the terms of the GNU General Public License.
 Source code: https://github.com/cp2k/cp2k
 License: GNU GPL v2. 

 * FFTW
 Author: Matteo Frigo
 Ref.: Frigo, M., Johnson, S.G.: “The design and implementation of FFTW3,” Proc. IEEE 93 (2), 216–231 (2005).
 Source code: http://www.fftw.org/download.html
 License: GNU GPL v2

 * GRAPHVIEW
 Author: Jonas Gehring
 Source code: https://github.com/jjoe64
 License: Apache License, Version 2.0

 * LAPACK
 Source code: https://netlib.org/lapack/
 License: freely-available software package, modified BSD license

 * LIBINT
 Author: Eduard Valeyev
 Source code: https://github.com/cp2k/libint-cp2k/releases
 License: GNU GPL v3

 * LIBXC
 Authors: Miguel AL Marques, Micael JT Oliveira, Tobias Burnus, Susi Lehtola, Conrad Steigemann
 Ref.: Lehtola, S., Steigemann C., Oliveira, M.J.T., Marques, M.A.L.: "Recent developments in libxc—A comprehensive library of functionals for density functional theory." SoftwareX 7 (2018): 1.
 Source code: http://www.tddft.org/programs/libxc/
 License: MPL
 
  * MOLCANVAS
 Authors: Alan Liska, Veronika Ruzickova (J. Heyrovsky Institute of Physical Chemistry of the CAS)
 Source code: https://github.com/alanliska/MolCanvas
 License: MIT

 * OPENBABEL
 Ref.: N M O'Boyle, M Banck, C A James, C Morley, T Vandermeersch, and G R Hutchison. "Open Babel: An open chemical toolbox." J. Cheminf. (2011), 3, 33. DOI:10.1186/1758-2946-3-33
       The Open Babel Package, version 2.3.1 http://openbabel.org (accessed Oct 2011)
 Source code: http://openbabel.org/wiki/Main_Page, https://github.com/openbabel/openbabel, https://sourceforge.net/projects/openbabel/

 * OPSIN
 Authors/developers: Rich Apodaca, Albina Asadulina, Peter Corbett, Daniel Lowe (Current maintainer), John Mayfield, Peter Murray-Rust, Noel O'Boyle, Mark Williamson
 Ref.: Lowe, Daniel M., Peter T. Corbett, Peter Murray-Rust, and Robert C. Glen. "Chemical name to structure: OPSIN, an open source solution." (2011): 739-753.
 Source code: https://github.com/dan2097/opsin
 License: MIT License

 * X11-BASIC
 Author: Markus Hoffmann
 Source code: https://github.com/kollokollo/X11Basic
 License: GPL-2.0

Manuals:

 * CP2K - own documentation

 * OpenBABEL: https://buildmedia.readthedocs.org/media/pdf/open-babel/latest/open-babel.pdf (CC0)

Sources of the pre-compiled binaries:

 * obabel: https://github.com/alanliska/openbabel-for-phreeqc-plus

 * xbbc: https://github.com/alanliska/X11-Basic-for-Android

 * xbvm: https://github.com/alanliska/X11-Basic-for-Android

 * cp2k related binaries: https://github.com/alanliska/CP2K-for-Android

Other references:

 * Basis sets for CP2K (file BASIS_Basis_Set_Exchange)
 A New Basis Set Exchange: An Open, Up-to-date Resource for the Molecular Sciences Community. Benjamin P. Pritchard, Doaa Altarawy, Brett Didier, Tara D. Gibson, Theresa L. Windus. J. Chem. Inf. Model. 2019, 59(11), 4814-4820, doi:10.1021/acs.jcim.9b00725.
 The Role of Databases in Support of Computational Chemistry Calculations Feller, D., J. Comp. Chem. 1996, 17(13), 1571-1586.
 Basis Set Exchange: A Community Database for Computational Sciences Schuchardt, K.L., Didier, B.T., Elsethagen, T., Sun, L., Gurumoorthi, V., Chase, J., Li, J., and Windus, T.L. J. Chem. Inf. Model. 2007, 47(3), 1045-1052, doi:10.1021/ci600510j.
 Website: https://www.basissetexchange.org/

 * OPENBABEL resources: parameter and other files taken from the official distribution site (http://openbabel.org/wiki/Main_Page, https://github.com/openbabel/openbabel, https://sourceforge.net/projects/openbabel/).

Info on licenses: please see the full-text licenses under the button Info on licenses. 

ACKNOWLEDGEMENTS: 

The authors appreciate the financial support from the GACR projects 18-12150S, 19-22806S, 21-23261S, 23-06465S, and internal (institutional) support RVO: 61388955. 
