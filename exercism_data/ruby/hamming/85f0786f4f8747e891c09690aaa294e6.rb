class Hamming
  def self.compute(first_strand, second_strand)
    min_length = [first_strand.length, second_strand.length].min
    first_strand.chars.zip(second_strand.chars)[0...min_length].count{|x,y| x != y}
  end
end
