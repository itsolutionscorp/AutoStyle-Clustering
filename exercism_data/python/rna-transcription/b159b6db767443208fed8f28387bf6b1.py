def to_rna(stone):
  
  stoneChang = stone.replace('G', 'g')
  dic = {'A':'U', 'T':'A', 'C':'G', 'g':'C'}

  for i,j in dic.iteritems():
    stoneChang = stoneChang.replace(i, j)

  return  stoneChang
