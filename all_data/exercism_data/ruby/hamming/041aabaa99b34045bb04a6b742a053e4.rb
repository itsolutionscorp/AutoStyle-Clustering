class Hamming
  def self.compute strand, other_strand
    strand       = strand.split ""
    other_strand = other_strand.split ""

    strand, other_strand = make_strands_even strand, other_strand

    compute_distance_between strand, other_strand
  end

  def self.make_strands_even strand, other_strand
    first_n_acids = [strand.count, other_strand.count].min
    strand        = strand.take first_n_acids
    other_strand  = other_strand.take first_n_acids

    [strand, other_strand]
  end

  def self.compute_distance_between strand, other_strand
    differences = 0
    0.upto(strand.count) do |i|
      differences += 1 if other_strand[i] != strand[i]
    end
    differences
  end
end
