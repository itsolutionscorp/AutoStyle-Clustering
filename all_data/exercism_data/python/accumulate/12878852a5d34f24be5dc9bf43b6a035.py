__author__ = 'oniwa'


def accumulate(lst, lam):
    nlst = []
    for item in lst:
        nlst.append(lam(item))
    return nlst
