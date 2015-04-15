class Hamming
  def self.compute(first_strand, second_strand)
    (0...[first_strand.length, second_strand.length].min).count do |i|
      first_strand[i] != second_strand[i]
    end
  end
end
