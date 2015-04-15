class DifferingLengthsError < StandardError
end

class Hamming
  def self.compute(ref_strand, comp_strand)
    compare_lengths(ref_strand, comp_strand)

    @ref_strand = ref_strand
    @comp_strand = comp_strand
    @hamming_distance = 0

    return @hamming_distance if exact_match?

    get_hamming_distance
    @hamming_distance

  rescue DifferingLengthsError
    puts "The length of reference strand and the length of the comparison strand do not match. The Hamming compute function only compares two nucleotide strands of the same length."
  end

  private
  def self.compare_lengths(ref_strand, comp_strand)
    if ref_strand.length != comp_strand.length
      raise DifferingLengthsError
    end
  end

  def self.exact_match?
    @ref_strand == @comp_strand
  end

  def self.get_hamming_distance
    (0..(@ref_strand.length - 1)).each do |idx|
      compare_nucleotides_at_position(idx)
    end
  end

  def self.compare_nucleotides_at_position(position)
    @hamming_distance += 1 unless @comp_strand[position] === @ref_strand[position]
  end
end
