def compute(a, b)
    a.each_char.each_with_index.count do |point, i|
      other_point = b[i]
      other_point && point != other_point
    end
  end