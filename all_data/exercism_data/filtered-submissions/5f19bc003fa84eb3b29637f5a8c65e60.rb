def compute(value1, value2)
    count = 0
    value1.chars.each_index do |c|
      if (value1[c] != value2[c])
        count += 1
      end
    end
    count
  end