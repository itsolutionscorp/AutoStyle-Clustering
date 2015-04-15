class Hamming

  def self.compute(strand, strand2)
  	mutual_length = [strand.length, strand2.length].min
    pairs = strand.chars.zip(strand2.chars).take(mutual_length)
    pairs.count {|x,y| x != y }
  end

end
