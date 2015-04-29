class Hamming
  def self.compute(first_sequence, second_sequence)
    s1,s2 = new_sequences(first_sequence, second_sequence)
    return 0 if sequence_match?(s1,s2)
    differences = 0
    iterations(s1,s2).times do |i|
      differences += 1 unless s1.at(i) == s2.at(i)
    end
    differences
  end

  def self.sequence_match?(s1,s2)
    s1.strand == s2.strand
  end

  def self.iterations(s1, s2)
    [s1.strand_length, s2.strand_length].min
  end

  def self.new_sequences(*strands)
    strands.map do |strand|
      Sequence.new(strand)
    end
  end

end

Sequence = Struct.new(:strand)
class Sequence
  def at(i)
    strand[i]
  end

  def to_s
    strand
  end

  def strand_length
    strand.length
  end
end
