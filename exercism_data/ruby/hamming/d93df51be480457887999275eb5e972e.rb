####### Submission for Hamming Exercism Problem #########
class Hamming
  def self.compute(strand, comparison_strand)
    return 0 if strand == comparison_strand
    strand_array = strand.split(//)
    comparison_strand_array = comparison_strand.split(//)
    compute_difference(strand_array, comparison_strand_array)
  end

  def self.compute_difference(strand_1, strand_2)
    shorter_strand, longer_strand = order_strands([strand_1, strand_2])
    hamming_difference = 0
    shorter_strand.each_with_index do |base, index|
      hamming_difference += 1 unless base == longer_strand[index]
    end
    hamming_difference
  end

  def self.order_strands(strands)
    strands.sort_by(&:size)
  end
end
