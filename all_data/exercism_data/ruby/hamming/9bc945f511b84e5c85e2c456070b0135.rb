class Hamming
  def self.compute(first_strand, second_strand)

    first_strand_array = first_strand.split(//)
    second_strand_array = second_strand.split(//)

    hamming_distance = 0

    first_strand_array.each_with_index.map do |element, index|
      break if index == second_strand_array.count
      hamming_distance += 1 if element != second_strand_array[index]
    end

    return hamming_distance
  end
end
