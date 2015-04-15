class Hamming
  def self.compute(strand_1, strand_2)
    strand_1_nucleotides = strand_1.chars
    strand_2_nucleotides = strand_2.chars

    output = 0

    strand_1_nucleotides.each_with_index do |char, index|
      break if strand_2_nucleotides[index].nil?

      if char != strand_2_nucleotides[index]
        output += 1
      end
    end

    output
  end
end
