class Hamming
  def compute(first, second)
    count = 0
    i = 0
    while i < first.length
      count += 1 if first[i] != second[i]
      i += 1
    end
    count
  end
end
