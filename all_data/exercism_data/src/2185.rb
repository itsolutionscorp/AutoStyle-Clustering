def compute(strand1, strand2)
     strand1.chars.zip(strand2.chars).count {|residue1, residue2| residue2 && residue1 != residue2}
  end