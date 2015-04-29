def compute(a,b)
    count = 0
    a.split("").map.with_index do |letter, i|
      count += 1 if letter != b[i]
    end
    count
  end