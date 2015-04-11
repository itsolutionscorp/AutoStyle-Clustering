class Hamming
  def self.compute(string1, string2)
    first_strand = string1.split('')
    second_strand = string2.split('')

    if second_strand.length < first_strand.length
      first_strand, second_strand = second_strand, first_strand
    end

    self.calculate_differences(first_strand, second_strand)
  end

  private

  def self.calculate_differences(first_strand, second_strand)
    diff = 0

    first_strand.each_with_index do |dna, i|
      if dna != second_strand[i]
        diff += 1
      end
    end

    diff
  end
end
