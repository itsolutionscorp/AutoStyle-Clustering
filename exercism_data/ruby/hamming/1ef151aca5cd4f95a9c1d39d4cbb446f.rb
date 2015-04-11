class Hamming
  def self.compute this_strand, that_strand
    difference = 0
    parallel_nucleotides(this_strand, that_strand) do |this_nucleotide, that_nucleotide|
      difference += 1 if (this_nucleotide != that_nucleotide)
    end
    difference
  end

  def self.parallel_nucleotides this_strand, that_strand
    for i in 0..(shortest_strand_length([this_strand, that_strand]) - 1) do
      yield this_strand[i], that_strand[i]
    end
  end

  def self.shortest_strand_length strands
    strands.map {|strand| strand.length}.min
  end
end
