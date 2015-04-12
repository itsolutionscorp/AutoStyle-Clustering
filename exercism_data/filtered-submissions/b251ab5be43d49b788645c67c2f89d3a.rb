def compute(a,b)
    distance = 0
    arr = a.chars
    arr.each_with_index do |c, index|
      distance = distance + 1 unless c == b[index]
    end
    distance
  end