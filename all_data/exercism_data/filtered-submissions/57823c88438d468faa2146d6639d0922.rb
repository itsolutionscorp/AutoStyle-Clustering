def compute(first, second)
    char_pairs = first.chars[0...second.length].zip(second.chars)
    char_pairs.count { |pair| pair.first != pair.last }
  end