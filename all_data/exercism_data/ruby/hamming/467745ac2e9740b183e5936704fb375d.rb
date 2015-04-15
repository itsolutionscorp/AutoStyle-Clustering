class Hamming
  def self.compute(first, second)
    diff_sequence interleave *normalize_sequences(first, second)
  end

  private

  def self.interleave(first, second)
    first.chars.zip(second.chars)
  end

  def self.diff_sequence(elements)
    elements.count {|char_a, char_b| char_a != char_b }
  end

  def self.normalize_sequences(first, second)
    min = [first.length, second.length].min
    return first[0...min], second[0...min]
  end
end
