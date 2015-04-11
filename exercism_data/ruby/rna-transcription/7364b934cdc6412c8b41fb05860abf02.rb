module Complement
  RCOMPDICT = {'G'=>'C','C'=>'G','T'=>'A','A'=>'U'}
  DCOMPDICT = RCOMPDICT.invert

  def self.of_dna (dseq)
    dseq.chars.map {|x| RCOMPDICT[x]}.join
  end
  def self.of_rna (rseq)
    rseq.chars.map {|x| DCOMPDICT[x]}.join
  end
end
