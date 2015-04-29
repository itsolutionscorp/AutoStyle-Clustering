class DNA
  def initialize(dna_string)
    @dna = dna_string.split(//)
  end

  def hamming_distance(other_dna_string)
    other_dna = other_dna_string.split(//)
    result_distance = 0
    @dna.each_with_index do |nucleotide, idx|
      other_nucleotide = other_dna[idx]
      result_distance +=1 if !other_nucleotide.nil? && other_nucleotide!=nucleotide
    end

    result_distance
  end
end
