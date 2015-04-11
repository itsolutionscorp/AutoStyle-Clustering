class Hamming
  def self.compute(first_strand, second_strand)
    @first_length, @second_length = first_strand.length, second_strand.length
    (0...[@first_length, @second_length].min).count do |i|
      first_strand.chars[i] != second_strand.chars[i]
    end
  end
end
