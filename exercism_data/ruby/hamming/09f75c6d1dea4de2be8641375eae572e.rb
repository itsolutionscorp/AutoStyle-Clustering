class Hamming
  def self.compute(strand1, strand2)
    count_mutations(strand1, strand2)
  end

  def self.count_mutations(strand1, strand2)
    counter = 0
    shortest_strand = find_smallest_strand(strand1, strand2)
    shortest_strand.times do |position|
      unless strand1[position] == strand2[position]
        counter += 1
      end
    end
    counter
  end

  def self.find_smallest_strand(strand1, strand2)
    [strand1.length, strand2.length].min {|a,b| a <=> b}
  end

end
