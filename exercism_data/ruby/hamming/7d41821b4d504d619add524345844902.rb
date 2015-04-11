class Hamming

  def self.compute(first_strand, second_strand)
    strands = self.parition_strands(first_strand, second_strand)

    differences = 0
    strands.first.each_with_index do |nucleic_acid, index|
      next if nucleic_acid == strands.last[index]
      differences += 1
    end

    differences
  end

  def self.parition_strands(first_strand, second_strand)
    ([first_strand.split("")] + [second_strand.split("")])
  end
end
