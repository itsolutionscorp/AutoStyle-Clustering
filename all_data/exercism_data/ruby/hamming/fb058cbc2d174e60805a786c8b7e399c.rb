require 'pry'

class Hamming

  def self.compute(strand, comparison_strand)
    return 0 if strand == comparison_strand
    strand_array = strand.split(//)
    comparison_strand_array = comparison_strand.split(//)
    compute_difference(strand_array, comparison_strand_array)
  end

  def self.compute_difference(strand_1, strand_2)
    longer_strand, shorter_strand = determine_strand_lengths(strand_1, strand_2)
    hamming_difference = 0
    shorter_strand.each_with_index do |base, index|
      hamming_difference += 1 unless base == longer_strand[index]
    end
    hamming_difference
  end

  def self.determine_strand_lengths(strand_1, strand_2)
    if strand_1.length > strand_2.length
      return strand_1, strand_2
    else
      return strand_2, strand_1
    end
  end

end
