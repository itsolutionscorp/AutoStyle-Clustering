class Hamming
  def self.compute(first_strand, other_strand)
    (0...[first_strand.size, other_strand.size].min).count do |i|
      first_strand[i] != other_strand[i]
    end
  end
end
