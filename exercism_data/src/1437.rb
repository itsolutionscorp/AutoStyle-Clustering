def compute(strand1, strand2)    
    l = [strand1.length, strand2.length].min - 1
    s1 = strand1.split(//)[0..l]
    s2 = strand2.split(//)[0..l]
    s1.zip(s2).count { |c1, c2| c1 != c2 }
  end