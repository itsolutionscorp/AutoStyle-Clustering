class Hamming
  # Computes the Hamming distance of two strands of DNA by
  # iterating over each array simultaneously and compares them
  def self.compute(original_strand, new_strand)
    original_strand_array = original_strand.split("")
    new_strand_array = new_strand.split("")

    compare_strands(original_strand_array, new_strand_array)
  end

  private

  # element-wise comparison of two nucleotide strands
  def self.compare_strands(strand1, strand2)
    mutations = 0

    strand1.each_with_index do |nucleotide, index|
      new_nucleotide = strand2[index] # corresponding nucleotide in new strand
      mutations += 1 unless no_hamming(nucleotide, new_nucleotide)
    end

    mutations
  end

  def self.no_hamming(nucleotide1, nucleotide2)
    # false unless both nucleotides are present and not equal to each other
    missing_nucleotide = (nucleotide1.nil? or nucleotide2.nil?)
    equal_nucleotides = (nucleotide1 == nucleotide2)
    return missing_nucleotide || equal_nucleotides
  end
end
