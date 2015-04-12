def compute(one, two)
    raise "Strands must be same size" if one.length != two.length
    score = 0
    one.chars.each_with_index do |char, index|
      score += 1 if char != two[index]
    end
    score
  end