import numpy as np

verbose = True

def Pa(x):
    return 1 + x + x ** 3 + x ** 7

def Pb(x):
    return x ** 3 + x ** 5 + x ** 6

def FFT(x, n):
    x = np.asarray(x, dtype = complex)
    N = x.shape[0]

    if N == 1: return [x[0] for _ in range(n)]
    else:
        X_even = FFT(x[::2], n // 2)
        X_odd = FFT(x[1::2], n // 2)
        wn = np.exp(2j * np.pi / n)
        w = 1
        result = np.zeros(n, dtype =complex)
        factors = []
        for i in range(n // 2):
            factors.append(w)
            result[i] = X_even[i] + w * X_odd[i]
            result[n // 2 + i] = X_even[i] - w * X_odd[i]
            w = w * wn
        if verbose:
            print(x)
            print("size: {}".format(n))
            print("roots: {}".format(factors))
            print("result:")
            for i, r in enumerate(result):
                print("{}: {}".format(i,r))
            print()
        return result

def IFFT(x, n):
    x = np.asarray(x, dtype = complex)
    N = x.shape[0]

    if N == 1: return [x[0] for _ in range(n)]
    else:
        X_even = IFFT(x[::2], n // 2)
        X_odd = IFFT(x[1::2], n // 2)
        wn = np.exp(-2j * np.pi / n)
        w = 1
        result = np.zeros(n, dtype =complex)
        factors = []
        for i in range(n // 2):
            factors.append(w)
            result[i] = X_even[i] + w * X_odd[i]
            result[n // 2 + i] = X_even[i] - w * X_odd[i]
            w = w * wn
        if verbose:
            print(x)
            print("size: {}".format(n))
            print("roots: {}".format(factors))
            print("result:")
            for i, r in enumerate(result):
                print("{}: {}".format(i,r))
            print()
        return result
    

def main():
    verbose = False
    A = FFT([1, 1, 0, 1, 0, 0, 0, 1], 16)
    B = FFT([0, 0, 0, 1, 0, 1, 1, 0], 16)

    C = A * B

    verbose = True

    result = [round(np.real(x)) for x in IFFT(C, 16) / 16]
    for i, r in enumerate(result):
        print("{}:{}".format(i,r))  


if __name__ == "__main__":
    main()

