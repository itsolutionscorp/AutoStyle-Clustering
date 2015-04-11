import string
#strand = ''
class DNA:

      def __init__(self,inString):
          self.strand = inString

      def to_rna(self):
          trans_table = string.maketrans('GCTA', 'CGAU')
          print self.strand.translate(trans_table)
          return self.strand.translate(trans_table)
#           As = list(re.finditer('A',self.strand))
#           Ts = list(re.finditer('T',self.strand))
#           Cs = list(re.finditer('C',self.strand))
#           Gs = list(re.finditer('G',self.strand))
#           strandString = list(self.strand)
#           if len(As) > 0:
#              for m in As:
#                  strandString[m.start(0)] = 'U'
#           if len(Ts) > 0:
#              for m in Ts:
#                  strandString[m.start(0)] = 'A'
#           if len(Cs) > 0:
#              for m in Cs:
#                  strandString[m.start(0)] = 'G'
#           if len(Gs) > 0:
#              for m in Gs:
#                  strandString[m.start(0)] = 'C'
#           self.strand = ''.join(strandString)
#           return self.strand


