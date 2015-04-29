#anagram.py

def detect_anagrams(x,*args):
    return [j for i in args for j in i if sorted([i.lower() for i in x])==sort_args(x,j)]    
def sort_args(x,args):
    return sorted([i.lower() for i in str(args) if x.lower()!= args.lower()])
