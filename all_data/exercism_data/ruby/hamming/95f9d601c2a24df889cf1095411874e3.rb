class Hamming
  def self.compute(strand1, strand2)
    count_mutations(strand1, strand2)
  end

  def self.count_mutations(strand1, strand2)
    shortest_strand = [strand1.length, strand2.length].min
    shortest_strand.times.count do |position|
      strand1[position] != strand2[position]
    end
  end

end
