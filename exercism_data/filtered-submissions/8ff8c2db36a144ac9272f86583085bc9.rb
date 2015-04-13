def compute(set1, set2)
    sum = 0
    set1.chars.each_with_index do |char, index|
      next if set2[index] == nil
      sum += 1 if char != set2[index]
    end
    sum
  end