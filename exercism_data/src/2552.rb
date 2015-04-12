def compute(first, second)
    char_pairs = first.chars[0...second.length].zip(second.chars)
    char_pairs.map { |pair| pair.first != pair.last ? 1 : 0 }.inject(:+)
  end
end