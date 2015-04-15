class Hamming

  def self.compute(strand1, strand2)
    first_strand = strand1.split('')
    second_strand = strand2.split('')
    difference = first_strand.zip(second_strand).find_all { |i, j| i != j }.count
  end

end
