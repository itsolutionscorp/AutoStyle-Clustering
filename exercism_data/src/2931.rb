class Hamming
  def compute(first, second)
    return -1 if first.size != second.size
    differences = 0
    first.each_char.with_index do |b, i|
      differences += 1 if b != second[i]
    end
    differences
  end
end
