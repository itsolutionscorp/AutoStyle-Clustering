def compute(a, b)
    a, b = [a, b].map(&:chars)
    a.zip(b).reduce(0) do |count, pair|
      pair.compact.uniq.size > 1 ? count.next : count
    end
  end