class Hamming
  def self.compute(first_strand, second_strand)
    distance = 0
    smaller_strand_length = [first_strand.length, second_strand.length].min
    first_strand.chars.take(smaller_strand_length).each_with_index do |first_strand_char, index|
      distance += 1 if (first_strand_char != second_strand.chars[index])
    end
    distance
  end
end
