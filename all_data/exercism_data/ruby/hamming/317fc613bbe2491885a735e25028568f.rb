class Hamming
  def self.compute dna_sequence_a, dna_sequence_b
    base_pairs = base_pairs(dna_sequence_a, dna_sequence_b)
    distances = base_pairs.map{|a,b| base_pair_distance(a,b)}
    distances.reduce(:+)
  end

  def self.base_pairs sequence_a, sequence_b
    sequences = [sequence_a, sequence_b]
    shortest_seq, longest_seq = sequences.sort_by(&:length).map(&:each_char)
    shortest_seq.zip(longest_seq)
  end

  def self.base_pair_distance a, b
    (a <=> b).abs
  end
end
