class DNA < Struct.new(:sequence)
  def hamming_distance(other)
    other_sequence = DNA.new(other).nucleotides
    similar_nucleotides = other_sequence.zip(nucleotides).select do |a, b|
      a && b && a != b
    end
    similar_nucleotides.length
  end

  def nucleotides
    sequence.chars
  end
end
