def compute(a,b)
    distance = 0
    a.split('').each_with_index do |val,i|
      distance += 1 if b[i] && val != b[i]
    end
    distance
  end