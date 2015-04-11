class Hamming
  def self.compute(strand, strand_two)
    count_mutations(strand, strand_two)
  end

  def self.count_mutations(strand, strand_two)
    counter = 0
    shortest_strand = compare_strands(strand, strand_two)
    shortest_strand.length.times do |position|
      unless strand[position] == strand_two[position]
        counter += 1
      end
    end
    counter
  end

  def self.compare_strands(strand, strand_two)
    [strand, strand_two].min {|a,b| a.length <=> b.length}
  end

end
