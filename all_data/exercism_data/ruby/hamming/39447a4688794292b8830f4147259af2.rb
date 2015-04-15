class DNA < Struct.new(:sequence)
  def hamming_distance(other)
    other_sequence = DNA.new(other).nucleotides
    distance = 0
    other_sequence.zip(nucleotides).each do |a, b|
      break unless a && b
      distance += 1 if a != b
    end
    distance
  end

  def nucleotides
    sequence.chars
  end
end
