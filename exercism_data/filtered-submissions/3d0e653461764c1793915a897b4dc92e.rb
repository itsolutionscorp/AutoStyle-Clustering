def compute(string_a, string_b)
    [string_a.split(''), string_b.split('')].transpose
                                            .map(&:uniq)
                                            .map(&:count)
                                            .count(2)
  end