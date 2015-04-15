class Hamming
  def self.compute(strand, other_strand)
    return 0 if uneven_strands?(strand, other_strand)

    if single_nucleotide_strands?(strand, other_strand)
      return strand == other_strand ? 0 : 1
    else
      return compute(strand.slice!(1), other_strand.slice!(1)) +
        compute(strand, other_strand)
    end
  end

  private

  # Returns true if not both strands have data
  def self.uneven_strands?(strand, other_strand)
    strand.nil? || other_strand.nil?
  end

  def self.single_nucleotide_strands?(strand, other_strand)
    strand.size == 1 && other_strand.size == 1
  end
end
