from string import maketrans

def to_rna(st):
  intrans = "ATCG"
  outtrans = "UAGC"
  trans = maketrans(intrans,outtrans)
  return st.translate(trans)
