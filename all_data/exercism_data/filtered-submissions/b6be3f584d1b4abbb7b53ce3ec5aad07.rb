def compute(x,y)
    differences = 0
    x.each_char.with_index do |character, index|
      differences += 1 if character != y[index]
    end
    return differences
  end