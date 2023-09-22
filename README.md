\documentclass[12pt, letterpaper]{article}
\usepackage{graphicx} 
\usepackage{amssymb}
\usepackage{amsmath}
\usepackage{centernot}
\usepackage{fancyhdr}

\title{MATH 431 Practice}
\author{Rohan Singh}
\date{Fall 2023}




\begin{document}

\maketitle

\tableofcontents

\newpage

%------------------------------------------------------------------

\section{Chapter 1: Matrix-Vector Multiplication}

\subsection{Definitions}
Let $x \in \mathbb{C}^n$, $A \in \mathbb{C}^{m \times n}$, then $b \in \mathbb{C}^m$:\\
$$b = Ax$$
$$b_i = \sum^n_{j=1} a_{ij}x_j$$
The mapping $x \to Ax$ is \textbf{linear}, which means that for any arbitrary $x, y \in \mathbb{C}^n$ and any arbitrary $\alpha \in \mathbb{C}$,
$$A(x+y) = Ax + Ay$$
$$A(\alpha x) = \alpha Ax$$
Conversely, every linear mapping between $\mathbb{C}^n \to \mathbb{C}^m$ can be represented as multiplication by a matrix in $\mathbb{C}^{m \times n}$.

\subsection{Matrix Times a Vector}
Let $A \in \mathbb{C}^{m \times n}$, $x \in \mathbb{C}^n$ and $a_j$ denote the $j^{th}$ column vector of the matrix $A$. Then we have $b \in \mathbb{C}^m$:
$$b = Ax = \sum^{n}_{j=1}x_ja_j$$

\subsection{Matrix Times a Matrix}
For the matrix-matrix product $A = BC$ where $A \in \mathbb{C}^{m \times n}$, we have the following conditions:
\begin{itemize}
    \item $B \in \mathbb{C}^{m \times l}$
    \item $C \in \mathbb{C}^{l \times n}$
\end{itemize}
If these conditions are satisfied, then each of the columns of the matrix $A$ is a linear combination of the columns of the matrix $B$.\\
\newpage 
Let $a_{ij}$, $b_{ik}$ and $c_{kj}$ be the respective entries of matrices $A$, $B$ and $C$. Then we can write $a_{ij}$ as:
$$a_{ij} = \sum^{l}_{k=1}b_{ik}c_{kj}$$
As eluded to before that the columns of $A$ can be represented as a linear combination of the columns of $B$. Let $a_j$ represent the $j^{th}$ column of matrix $A$,  $b_k$ represent the $k^{th}$ column of matrix $B$ and $c_j$ represent the $j^{th}$ column of matrix $C$.
$$a_j = Bc_j = \sum^{l}_{k=1}c_{kj}b_k$$
Thus $a_j$ is a linear combination of $b_k$ with coefficients $c_{kj}$ $\forall k \in [0..l]$.

\subsection{Range and Nullspace}
The \textbf{Range} of a matrix $A$ is defined as the set of vectors that vectors that can be expressed as a product $Ax$. It is also known as the \textbf{column space}. Formally, for a matrix $A \in \mathbb{C}^{m \times n}$:
$$R = \{v = Ax, \forall x \in \mathbb{C}^n \}$$\\
On the other hand, the \textbf{Nullspace} of a matrix $A$ is defined as the set of vectors such that they satisfy the equation $Ax = 0$.  Formally, for a matrix $A \in \mathbb{C}^{m \times n}$:
$$null(A) = \{\forall x \in \mathbb{C}^n: Ax = 0 \}$$

\subsection{Rank}
The \textbf{column rank} of a matrix $A$ is the dimension of its column space. Similarly, the \textbf{row rank} of a matrix $A$ is the dimension of the space spanned by it's rows. Since column rank is the same as the row rank, we just simply use rank.\\\\
A matrix $A \in \mathbb{C}^{m \times n}$ is said to have \textbf{full rank} if the matrix has it's rank equal to \textbf{min}$(m,n)$. This means that a full rank matrix $A \in \mathbb{C}^{m \times n}$, where $m \geq n$, must have $n$ linearly independent columns.

\subsection{Inverse}
A matrix $A$ is an \textbf{invertible} or \textbf{non-singular} matrix if it is a square matrix and has full rank. Formally, $A$ is invertible iff:
$$A \in \mathbb{C}^{m \times m}$$
$$rank(A)= m$$
We can use the $m$ column vectors of $A$ to form a basis for the whole space $\mathbb{C}^m$, in other words, we can express every vector $v \in \mathbb{C}^m$ as a linear combination of the $m$ columns of $A$.\\\\
Any non-singular/invertible matrix $A \in \mathbb{C}^{m \times m}$ has a unique \textbf{inverse matrix}, $A^{-1} \in \mathbb{C}^{m \times m}$, such that:
$$AA^{-1} = A^{-1}A = I$$
where $I$ is the \textbf{Identity Matrix} of $\mathbb{C}^{m \times m}$.\\\\
For two invertible matrices $A$ and $B \in \mathbb{C}^{m \times m}$, we have:
$$(AB)^{-1} = B^{-1}A^{-1}$$

\subsection{Matrix Inverse times a vector}
For the linear system given by:
$$Ax = b$$
Where $A \in \mathbb{C}^{m \times m}$ is the matrix of the coefficients of the linear system, $b \in \mathbb{C}^m$ is the vector of coefficient of expansion $\&$ $x \in \mathbb{C}^m$ is the unique vector that satisfies the linear system. For $x$ to be a unique solution to the linear system, the matrix $A$ must be a non-singular/invertible matrix.\\\\
To obtain the unique solution of such a system:
$$Ax = b$$
$$A^{-1}Ax = A^{-1}b$$
$$Ix = A^{-1}b$$
$$x = A^{-1}b$$
\newpage

%------------------------------------------------------------------

\section{Chapter 2: Orthogonal Vectors and Matrices}

\subsection{Adjoint}
For a matrix $A \in \mathbb{C}^{m \times n}$, its \textbf{Hermitian Conjugate} or \textbf{Adjoint} matrix $A^* \in \mathbb{C}^{n \times m}$, such that the $ji^{th}$ entry of $A^*$ is the complex conjugate of the $ij^{th}$ entry of $A$.\\\\
A matrix $A$ is said to be a \textbf{Hermitian Matrix} iff $A = A^*$. By definition (and common sense) every Hermitian Matrix must be a square matrix.\\\\
Additionally for two matrices $A$ and $B$ of compatible dimensions. We have the following:
$$(AB)^* = B^*A^*$$

\subsection{Inner Product}
The \textbf{inner product} of two column vectors $x, y \in \mathbb{C}^m$ is the product of the adjoint of $x$ by $y$:
$$x^*y = \sum^{m}_{i=1}x_i^*y_i$$
The \textbf{Euclidean Norm} of a vector $x \in \mathbb{C}^m$ can be defined as the square root of the of the inner product of the vector $x$ with itself:
$$\|x\| = \sqrt{x^*x} = (\sum^m_{i=1}{|x_i|^2})^{1/2}$$
The cosine angle $\alpha$ between two vectors $x$ and $y$ can be represented as:
$$\cos{\alpha} = \frac{x^*y}{\|x\|\|y\|}$$
The inner product is \textit{bilinear} which means that it is linear in each vector seperately:
$$(x_1 + x_2)^*y = x_1^*y + x_2^*y$$
$$x^*(y_1 + y_2) = x^*y_1 + x^*y_2$$
$$(\alpha x)^*(\beta y) = \alpha^*x^*\beta y$$

\subsection{Orthogonal Vectors}
A pair of vectors $x, y \in \mathbb{C}^m$ are said to be orthogonal iff:
$$x^*y= 0$$
A set of nonzero vectors are said to be \textbf{orthogonal} iff all of the vectors in the set $S$ are pairwise orthogonal, formally:
$$\forall x,y \in S, x \neq y \implies x^*y = 0$$
A set of vectors are said to be \textbf{orthonormal} if the set of vectors are orthogonal and in addition have norm equal to one. Formally:
$$\forall x \in S, \|x\| = 1$$

\subsection{Components of Vector}
Let the set $\{q_i\}$ be an \textbf{orthogonal} set of $m$ vectors, they act as a \textbf{basis set} for $\mathbb{C}^m$. In other words, then any arbitrary vector $v \in \mathbb{C}^m$ can be written as the linear combination of the set $\{q_i\}$ with coefficients equal to the inner product with the $v$, also known as the \textit{orthogonal projections}. Formally:
$$v = \sum^m_{i=1}{(q_i^*v)q_i}$$

\subsection{Unitary Matrices}
A Matrix $Q \in \mathbb{C}^{m \times m}$ is said to be \textbf{Unitary} or \textbf{Orthogonal}, if:
$$Q^* = Q^{-1}$$
$$Q^*Q = QQ^* = I$$

\subsection{Multiplication by Unitary Matrices}
Let the matrix $A$ for a linear transformation be a Unitary Matrix. $Q \in \mathbb{C}^{m \times m}$ and $x, b \in \mathbb{C}^m$ then:
$$Qx = b$$
$$x = Q^*b$$
For $Q \in \mathbb{C}^{m \times m}$ and $x, y \in \mathbb{C}^m$ then:
$$(Qx)^*(Qy) = x^*Q^*Qy = x^*y$$
The invariance of the inner product after multiplying it with a Unitary Matrix means:
$$\|Qx\| = \|x\|$$
\newpage

%------------------------------------------------------------------

\section{Chapter 3: Norms}
Norms are used to capture notions of size and distance in vector spaces. Norms are used to measure \textit{approximations} and \textit{convergence} in Numerical Linear algebra applications.

\subsection{Vector Norms}
A \textbf{Norm} is a function that assigns a real-valued length to each vector, Formally:  
$$\|\cdot\|:\mathbb{C}^m\to\mathbb{R}$$
Any norm must satisfy the following 3 conditions $\forall x,y \in \mathbb{C}^m$ and $\forall \alpha \in \mathbb{C}$:
$$\|x\| \geq 0, \|x\| = 0 \iff x = 0$$
$$\|x+y\| \leq \|x\| + \|y\|$$
$$\|\alpha x\| = |\alpha| \|x\|$$


\subsection{Matrix Norms induced by Vector Norms}
For matrix $A \in \mathbb{C}^{m \times n}$ an induced matrix norm is given by:
$$\|A\|_{(m,n)} = \sup_{x \in \mathbb{C}^n} \frac{\|Ax\|_{(m)}}{\|x\|_{(n)}} = \sup_{\|x\|_{(n)} = 1}\|Ax\|_{(m)}$$\\
The induced \textbf{1-norm} for a matrix $A \in \mathbb{C}^{m \times n}$, where the columns are given by: $\{a_1, a_2....a_n\}$ can be given by:
$$\|A\|_1 = \max_{1 \leq j \leq n}\|a_j\|_1$$\\
The induced \textbf{$\infty$-norm} for a matrix $A \in \mathbb{C}^{m \times n}$, where the rows are given by: $\{a^*_1, a^*_2....a^*_m\}$ can be given by:
$$\|A\|_{\infty} = \max_{1 \leq i \leq m}\|a^*_i\|_{1}$$\\

\subsection{Cauchy-Schwartz and Hölder Inequalities}
Let $1 \leq p,q \leq \infty$ and $x, y \in \mathbb{C}^m$ and $p, q$ satisfy $\frac{1}{p} + \frac{1}{q} = 1$. Then the \textbf{Hölder Inequality} states that:
$$|x^*y| \leq \|x\|_p\|y\|_q$$\\
\textbf{The Cauchy-Schwartz inequality} is the specific case of the Hölder Inequality, where $p = q = 2$:
$$|x^*y| \leq \|x\|_2\|y\|_2$$

\subsection{General Matrix Norms}
Any matrix norm must satisfy the following 3 conditions $\forall A,B \in \mathbb{C}^{m \times n}$ and $\forall \alpha \in \mathbb{C}$:
$$\|A\| \geq 0, \|A\| = 0 \iff A = 0$$
$$\|A+B\| \leq \|A\| + \|B\|$$
$$\|\alpha A\| = |\alpha| \|A\|$$\\
\textbf{The Forbinius-Norm} or \textbf{Hilbert-Schmidt Norm} is one of the most important general (non-vector induced) norms defined by:
$$\|A\|_F = (\sum^m_{i=1}\sum^n_{j=1}|a_{ij}|^2)^{1/2}$$\\
The Forbenius Norm may also be written in the following form, where $a_j$ refers to the $j^{th}$ column vector and $a^*_i$ is the $i^{th}$ row vector of the matrix $A$:
$$\|A\|_F = (\sum^{n}_{j=1}\|a_j\|^2_2)^{1/2}$$
$$\|A\|_F = (\sum^{m}_{i=1}\|a^*_i\|^2_2)^{1/2}$$\\
Another analagous result is:
$$\|A\|_F = \sqrt{tr(A^*A)} = \sqrt{tr(AA^*)}$$
For $C = AB$ where $A$ and $B$ are compatible matrices under multiplication, the Forbenius Norm for the product of the two matrices is given by:
$$\|C\|_F = \|AB\|_F = \|A\|_F\|B\|_F$$

\subsection{Invariance under Unitary Multiplication}
For $A \in \mathbb{C}^{m \times n}$ and a \textit{Unitary Matrix} $Q \in \mathbb{C}^{m \times m}$, we have:
$$\|QA\|_2 = \|A\|_2$$
$$\|QA\|_F = \|A\|_F$$
\newpage

%------------------------------------------------------------------

\section{Chapter 4: The Singular Value Decomposition}

\subsection{Motivation}
The SVD of a Matrix is motivated by the geometric fact that the image of each $m \times n$ matrix is a \textbf{hyperellipse}. You might be wondering what a hyperellipse really is? A hyperellipse is simply an $m$-dimensional generalization of an ellipse.\\\\
In the field of mathematics however, we must use formality and not hand-wavy generalizations. So we can define a hyperelipse in $\mathbb{R}^m$ as the surface obtained by stretching the unit sphere in $\mathbb{R}^m$ by some factors $\sigma_1,\sigma_2...\sigma_m$ (possibly zero) in some orthogonal directions $u_1,u_2...u_m \in \mathbb{R}^m$

\subsection{Formal Definition}
The \textbf{Singular Value Decomposition} of a matrix $A \in \mathbb{C}^{m \times n}$ is a unique factorization:
$$A = U\Sigma V^*$$
Such that:
$$U \in \mathbb{C}^{m \times m} \quad \text{ is Unitary}$$
$$V \in \mathbb{C}^{n \times n} \quad \text{ is Unitary}$$
$$\Sigma \in \mathbb{C}^{m \times n} \quad \text{ is Diagonal}$$
Additionally, the diagonal entries of $\sigma_j$ of $\Sigma$ are in non-decreasing order, i.e. $\sigma_1 \geq \sigma_2 \geq ... \sigma_p \geq 0$, where $p = \textit{min}(m,n)$.\\\\
Note: $\Sigma$ is always the same dimensions as the matrix $A$, and the matrices $U,V$ are always unitary.

\subsection{Diagonal Matrix representation}
The SVD of a matrix $A \in \mathbb{C}^{m \times n}$ makes it possible for us to define it as a diagonal matrix, if we use the proper bases for the domain and range spaces. The change of bases works by expanding any $b \in \mathbb{C}^m$ in the basis of the columns $U$ and $x \in \mathbb{C}^n$ in the basis of the columns of $V$. After the change of bases we get:
$$b' = U^*b$$
$$x' = V^*x$$
We can hence express the relation $Ax = b$ in terms of $x', b'$ by:
$$Ax = b$$
$$U\Sigma V^*x = b$$
$$U^*U\Sigma V^*x = U^*b$$
$$\Sigma V^*x = U^*b$$
$$\Sigma x' = b'$$
Thus, the SVD reduces $A$ to a diagonal matrix $\Sigma$ when the domain is expressed in the basis of the columns of $V$ and the range is expressed in the basis of the columns of $U$.

\subsection{Singular Values and Rank}
The aforementioned representation of a matrix $A$ as a diagonal matrix using the SVD of $A$, helps us obtain various properties of the matrix $A$ such as the rank of $A$ and additionally the bases of the range and null-space of $A$.\\\\
Let $r$ be the number of non-zero singular values of $\Sigma$, we know that the rank of $\Sigma$ is hence equal to $r$. Additionally, in the decomposition $A = U\Sigma V^*$, we know that $U$ and $V$ are full rank. Therefore:
$$\text{rank}(A) = \text{rank}(\Sigma) = r$$\\
We also know that (by virtue of $\Sigma$ being a diagonal matrix), where $\Sigma$ has $r$ non-zero singular values:
$$\text{range}(\Sigma) = \langle e_1, e_2,... e_r\rangle \subseteq \mathbb{C}^m$$
$$\text{null}(\Sigma) = \langle e_r+1, e_r+2,... e_n\rangle \subseteq \mathbb{C}^n$$
We can use the change of bases argument for $A$ to get:
$$\text{range}(A) = \langle u_1, u_2,... u_r\rangle \subseteq \mathbb{C}^m$$
$$\text{null}(A) = \langle v_r+1, v_r+2,... v_n\rangle \subseteq \mathbb{C}^n$$

\subsection{Matrix Norms and Singular Values}
In the previous chapter we defined the 2-norm of a matrix $A$ but didn't really obtain an expression to calculate it. The 2-norm can in fact be very easily calculated using the SVD of $A$. Before we get to the 2-norm of A, we shall calculate the 2-norm of $\Sigma$:
$$\|\Sigma\|_2 =  \sup_{\|x\|_{(2)} = 1}\|\Sigma x\|_{(2)} = \text{max}(|\sigma_j|) = \sigma_1$$
In the latter part of the last chapter we proved the invariance of the 2-norm under unitary multiplication (See Chapter 3.5), we can hence obtain:
$$\|A\|_2 = \|\Sigma\|_2 = \sigma_1$$\\
To obtain the Frobenius norm of $\Sigma$, we can use the formula to calculate the Frobenius norm, given by:
$$\|\Sigma\| = \sqrt{tr(\Sigma^*\Sigma)}$$
In the latter part of the last chapter we proved the invariance of the Frobenius-norm under unitary multiplication (See Chapter 3.5), we can hence obtain:
$$\|A\|_F = \|\Sigma\|_F = \sqrt{\sigma_1^2 + \sigma_2^2 + ... + \sigma_r^2}$$

\subsection{Determinants using SVD}
As you might've expected, SVDs also help us to obtain the determinant of a \textbf{Square Matrix} $A \in \mathbb{C}^{m \times m}$. We know that the absolute value of the determinant of a Unitary Matrix is 1, so we obtain the formula:
$$|\text{det}(A)| = |\text{det}(U\Sigma V^*)| = |\text{det}(U)||\text{det}(\Sigma)||\text{det}(V^*) = |\text{det}(\Sigma)| = \prod_{i=1}^m\sigma_i$$
This also leads to the corollary that the determinant of a square matrix is 0 iff the matrix is not full rank iff the matrix is non-invertible.
\newpage

%------------------------------------------------------------------

\section{Chapter 5: The Fundamental Subspaces}
\subsection{Introduction}
For any linear transformation given by $Ax = b$ with $A \in \mathbb{C}^{m \times n}, x \in \mathbb{C}^n, b \in \mathbb{C}^m$. The Matrix $A$ has certain properties that define the linear transformation, which are called the \textbf{fundamental subspaces of $A$} or the \textbf{Four Fundamental Subspaces}. These include:
\begin{enumerate}
    \item \textbf{Range($A$):} This is defined as the vector subspace spanned by the columns of the Matrix $A$
    \item \textbf{Null($A$):} This is defined as the vector subspace that can span $Ax=0_V$
    \item \textbf{Range($A^T$):} This is defined as the vector subspace spanned by the columns of the Matrix $A^T$
    \item \textbf{Null($A^T$):} This is defined as the vector subspace that can span $A^Ty=0_V$
\end{enumerate}

\subsection{The Subspaces}
The dimensions and bases of the four fundamental subspaces are defined by the \textbf{rank}, $r$,  of the matrix $A$, which is the same as the number of linearly independent columns of $A$ or the number of non-zero singular values of $A$.\\\\
As eluded to in the previous chapter, we can find the bases of these subspaces using SVD. Using this information, we can now formally define the four fundamental subspaces of $A \in \mathbb{C}^{m \times n}$:
\begin{enumerate}
    \item \textbf{Range($A$)}
    $$\text{Range}(A) = \{y \in \mathbb{C}^m: y = Ax\}$$
    $$\text{Basis(Range}(A)) = \langle u_1, u_2 ... u_r\rangle$$
    $$\text{dim(Range}(A)) = r$$
    \item \textbf{Null($A$)}
    $$\text{Null}(A) = \{x \in \mathbb{C}^n: Ax = 0\}$$
    $$\text{Basis(Null}(A)) = \langle v_r, v_{r+1} ... v_n\rangle$$
    $$\text{dim(Null}(A)) = n-r$$
    \item \textbf{Range($A^T$)}
    $$\text{Range}(A^T) = \{x \in \mathbb{C}^n: x = A^Ty\}$$
    $$\text{Basis(Range}(A^T)) = \langle v_1,v_2 ... v_r\rangle$$
    $$\text{dim(Range}(A^T)) = r$$
    \item \textbf{Null($A^T$)}
    $$\text{Null}(A^T) = \{y \in \mathbb{C}^m: A^Ty=0\}$$
    $$\text{Basis(Null}(A^T)) = \langle u_{r+1}, u_{r+2} ... u_m\rangle$$
    $$\text{dim(Null}(A^T)) = m-r$$
\end{enumerate}

\subsection{Orthogonality}
Through the formal definition of the 




\end{document}
