module Hamming
  def compute(first, second)
    first, second = [first.chars, second.chars].sort_by(&:size)
    first.zip(second).count { |(one, two)| one != two }
  end
end
