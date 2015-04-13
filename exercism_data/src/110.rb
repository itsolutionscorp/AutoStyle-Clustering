def compute(a, b)
    a.chars.zip(b.chars).reject { |pair| pair[1].nil? || pair[0] == pair[1] }.count
  end