def compute(a, b)
      pairs = a.chars.zip(b.chars).reject do |pair|
        pair[0].nil? || pair[1].nil?
      end
      pairs.inject(0) do |total, pair|
        pair[0] == pair[1] ? total : total + 1
      end
    end