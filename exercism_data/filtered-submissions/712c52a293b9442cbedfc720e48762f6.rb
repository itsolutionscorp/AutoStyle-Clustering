def compute(first, second)
    result = ""
    first.size.times{|i| result << first[i].delete(second[i] || first[i])}
    result.size
  end