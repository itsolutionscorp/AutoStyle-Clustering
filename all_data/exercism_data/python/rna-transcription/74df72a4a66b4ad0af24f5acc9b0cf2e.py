def to_rna(dnaString):
   convertedDNAString = ""
   for i, c in enumerate(dnaString):
       if c == 'G':
           convertedDNAString += 'C'
       elif c == 'C':
           convertedDNAString += 'G'
       elif c == 'T':
           convertedDNAString += 'A'
       elif c == 'A':
           convertedDNAString += 'U'
   return convertedDNAString
