class DNA(object):
  def __init__(self,nucl):
    self.nucl = nucl

  def to_rna(self):
    if len(self.nucl) == 0:
      return ''
    else:
      seq = ""
      for c in self.nucl:
	seq += self.nucl_to_rna(c)
      return seq
     

  def nucl_to_rna(self,n):
    if n == 'A':
      return 'U'
    elif n == 'T':
      return 'A'
    elif n == 'C':
      return 'G'
    elif n == 'G':
      return 'C'
    else:
      return ''
