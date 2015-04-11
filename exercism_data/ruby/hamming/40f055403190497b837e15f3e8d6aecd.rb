# hamming.rb
class Hamming

  def self.compute(strand1, strand2)
    if strand2.length < strand1.length
      strand1, strand2 = strand2, strand1
    end
    zippedStrands = strand1.chars.zip(strand2.chars)
    zippedStrands.count{ |x| x[0] != x[1]}
  end

end
