def compute(s1, s2)
  min_size = [s1.chars.count, s2.chars.count].min
  s1 = s1[0..min_size-1]
  s2 = s2[0..min_size-1]

  pairs = s1.chars.zip(s2.chars)
  count = 0

  pairs.each do |pair|
    if pair[0] != pair[1]
      count += 1
    end
  end
  count
  end