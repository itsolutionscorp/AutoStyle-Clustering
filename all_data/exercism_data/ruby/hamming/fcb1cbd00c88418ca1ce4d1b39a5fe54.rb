class Hamming

  def self.compute(a, b)
    first_strand = a.split('')
    second_strand = b.split('')
    difference = first_strand.zip(second_strand).find_all { |i, j| i != j }.count
  end

end
