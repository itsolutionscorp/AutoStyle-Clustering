class Hamming
  def compute(a, b)
    max = [a.length, b.length].max
    c = a.chars.first(max).zip(b.chars.first(max))
    c.inject(0) {|diff, i| diff += 1 if i.uniq.size == 2; diff }
  end
end
