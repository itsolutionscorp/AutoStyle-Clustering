#anagram.py

def detect_anagrams(x,*args):
    p=[i.lower() for i in x]
    p.sort()    
    for i in args:
	a= [j for j in i if sort_args(x,j)==p]
    return a
		
def sort_args(x,args):
    if x.lower()!= args.lower():
        j=[i.lower() for i in str(args)]
        j.sort()
        return j       