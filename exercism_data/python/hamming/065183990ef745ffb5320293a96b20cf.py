#It could be shorter without defining this seperate function, but
#I wanted to do it this way because it emphasizes what it going on with
#the strings more. And a string difference function could possibly be used in
#other places (say, to locate where the mutations are)

def string_dif(A,B):

    A=list(A)
    B=list(B)
    C=[]

    if len(A)!=len(B):
        return

    for i in range(0,len(A)):
        C.append(A[i]!=B[i])
    
    return C

def distance(A,B):
    C = string_dif(A,B)
    n=0

    for i in range(0,len(C)):
        n=n+C[i]

    return n
