class Hamming
  def self.compute(strand, strand_two)
    count_mutations(strand, strand_two)
  end

  def self.count_mutations(strand, strand_two)
    counter = 0
    strands = [strand, strand_two]
    shortest_strand = strands.min {|a,b| a.length <=> b.length}
    shortest_strand.length.times do |position|
      unless strand[position] == strand_two[position]
        counter += 1
      end
    end
    counter
  end

end
