class DNA

  def initialize(sequence)
    @sequence = sequence
  end

  def hamming_distance(other_sequence)
    mutations = 0
    each_nucleotide_pair(other_sequence) do |nucleotide, other_nucleotide|
      mutations += 1 if nucleotide != other_nucleotide
    end
    mutations
  end

  private

  attr_reader :sequence

  def each_nucleotide_pair(other_sequence)
    sequence.chars.zip(other_sequence.chars).each do |pair|
      yield pair unless pair.include?(nil)
    end
  end

end
