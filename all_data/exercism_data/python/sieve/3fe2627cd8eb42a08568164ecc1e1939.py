def sieve(num):
    n=2
    primes={}
    #populates the list with numbers 2-num
    while(n < num):
        primes[n]=True
        n+= 1
    
    n=1
    for p in primes:
        if(primes[p]):
            n=p+1
            while(n<num):
                if(isMult(n,p)):
                    primes[n]=False
                n+=1
        
    
        
    return makeList(primes)

def isMult(num1,num2):
    left=num1%num2
    if(left==0):
        return True
    return False

def makeList(primes):
    listP=[]
    
    for p in primes:
        if(primes[p]):
            listP.append(p)
    return listP
            

print sieve(1000)
    
