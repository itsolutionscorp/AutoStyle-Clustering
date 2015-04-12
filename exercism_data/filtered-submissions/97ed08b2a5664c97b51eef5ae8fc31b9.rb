class Hamming
  def compute(a, b)
    a.split("").zip(b.split("")).reject { |pair| pair.first == pair.last }.count
  end
end
