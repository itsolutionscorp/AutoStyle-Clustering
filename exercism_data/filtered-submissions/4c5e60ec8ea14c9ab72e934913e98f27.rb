class Hamming
  def compute(dnaA,dnaB)
    [dnaA.chars.length, dnaB.chars.length].min.times.count{ |i|  dnaA[i] != dnaB[i] }
  end
end
