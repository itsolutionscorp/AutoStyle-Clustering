class Hamming
  def compute(first_strand, second_strand)
    hamming_distance = 0

    first_strand.each_char.with_index do |char, index|
      hamming_distance += 1 if second_strand[index] != char
    end

    hamming_distance
  end
end
