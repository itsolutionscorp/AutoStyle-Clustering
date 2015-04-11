class Hamming
  def self.compute strand, other_strand
    strand       = strand.split ""
    other_strand = other_strand.split ""

    first_n_acids = take_shortest_length strand, other_strand

    strand       = strand.take first_n_acids
    other_strand = other_strand.take first_n_acids

    compute_distance_between strand, other_strand
  end

  def self.take_shortest_length strand, other_strand
    [strand.count, other_strand.count].min
  end

  def self.compute_distance_between strand, other_strand
    differences = 0
    0.upto(strand.count) do |i|
      differences += 1 if other_strand[i] != strand[i]
    end
    differences
  end
end
