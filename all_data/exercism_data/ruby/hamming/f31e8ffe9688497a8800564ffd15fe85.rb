class Hamming
  def self.compute(first_strand, second_strand)

    first_split = first_strand.split("")
    second_split = second_strand.split("")

    strand_length = first_split.length
    hamming_count = 0

    (0..(strand_length-1)).each do |index|
      if (first_split[index] != second_split[index])
      hamming_count += 1
      end
    end
    hamming_count
  end
end
