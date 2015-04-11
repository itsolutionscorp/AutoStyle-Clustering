module Complement
  @rcompDict = {'G'=>'C','C'=>'G','T'=>'A','A'=>'U'}

  def self.of_dna (dseq)
    rcomp = ''
    dseq.each_char {|x| rcomp<<@rcompDict[x]}
    return rcomp
  end
  def self.of_rna (rseq)
    dcompDict = @rcompDict.invert
    dcomp = ''
    rseq.each_char {|x| dcomp<<dcompDict[x]}
    return dcomp
  end
end
