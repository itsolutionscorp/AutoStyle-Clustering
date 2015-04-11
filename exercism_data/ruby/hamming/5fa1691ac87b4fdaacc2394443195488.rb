class Hamming
  def self.compute(first, second)
    first, second = normalize_sequences(first, second)
    diff_sequence(interleaved_sequences(first, second))
  end

  private

  def self.interleaved_sequences(first, second)
    first.chars.zip(second.chars).flatten.compact
  end

  def self.diff_sequence(elements)
    res = 0
    elements.each_slice(2) {|pair| res += 1 if pair[0] != pair[1] }
    res
  end

  def self.normalize_sequences(first, second)
    comparison = first <=> second
    if(comparison > 0)
      first = first.slice(0, second.length)
    else
      second = second.slice(0, first.length)
    end
    return first, second
  end
end
