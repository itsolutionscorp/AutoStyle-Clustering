def compute a, b
    sum = 0;
    limit = [a.length, b.length].min
    (0...limit).each do |i|
      sum += 1 if a[i] != b[i]
    end
    sum
  end