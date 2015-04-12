def compute(first,second)
    sum = 0
    first.scan(/./).each_with_index { |x,i| sum += 1 if first[i] != second[i]}
    sum
  end