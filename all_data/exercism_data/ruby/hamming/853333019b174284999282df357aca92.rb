class Hamming

  def self.compute(strandA,strandB)
    strandA.chars.zip(strandB.chars).select{|a,b| a!=b && (a.nil? || b.nil?) ==false }.count
  end
end
