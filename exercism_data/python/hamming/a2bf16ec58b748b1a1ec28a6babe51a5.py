import sys

dnaSuperset = set(['G','C','T','A'])

def distance(dna1, dna2):
    errors = 0
    errorStatement = ''
    dna1 = dna1.upper()
    dna2 = dna2.upper()
    if len(dna1) != len(dna2):
        errorStatement += "The two DNA strands are of different length. " 
        errorStatement += "Hamming distance is undefined in this case.\n"
        errors += 1
    if not(set(list(dna1)) <= dnaSuperset):
        errorStatement += "The first DNA strand has amino acid designations "
        errorStatement += "not part of proper DNA (A,C,G,T).\n"
        errors += 1
    if not(set(list(dna2)) <= dnaSuperset):
        errorStatement +=  "The second DNA strand has amino acid designations "
        errorStatement +=  "not part of proper DNA (A,C,G,T).\n"
        errors += 1
    
    if errors == 0:
        hammingDistance = 0
        for n in range(0,len(dna1)):
            if dna1[n] != dna2[n]:
                hammingDistance += 1
        return hammingDistance
    else:
        verb = "was" if errors == 1 else "were"
        plural = "" if errors == 1 else  "s"
        print "There %s %s error%s:"  % (verb, errors, plural)
        print errorStatement
        print "Please reexamine your submission and try again."    
           

if __name__ == '__main__':
    print "hamming distance: %d" % distance(sys.argv[1],sys.argv[2])
