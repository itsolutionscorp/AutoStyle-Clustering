def compute(first, second)
    first.chars.zip(second.chars).reject { |pair| pair[0].eql?(pair[1]) }.length
  end