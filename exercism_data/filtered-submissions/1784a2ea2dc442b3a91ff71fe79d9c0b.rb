class Hamming
  def compute(first, second)
    first  = first.chars
    second = second.chars
    size = [first.size, second.size].min
    first.take(size).zip(second.take(size)).count { |(one, two)| one != two }
  end
end
