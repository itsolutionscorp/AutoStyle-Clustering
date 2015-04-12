def compute(x, y)
    count = 0
    x.split('').each_with_index do |char, i|
      if char != y[i] && y[i]
        count += 1
      end
    end
    count
  end