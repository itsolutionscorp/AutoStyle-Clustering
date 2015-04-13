def compute first, other
    count = 0
    minimum = [first.size, other.size].min
    first[0...minimum].chars.each_with_index do |char, index|
      count += 1 if char != other[index]
    end
    count
  end