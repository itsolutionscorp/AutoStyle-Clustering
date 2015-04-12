class Hamming
  def compute(first, second)
    result = 0
    length = first.length <= second.length ? first.length : second.length

    length.times.each {|i| result += 1 if first[i] != second[i]}

    result
  end
end
