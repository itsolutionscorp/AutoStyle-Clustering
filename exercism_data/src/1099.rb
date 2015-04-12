def compute(strand1,strand2)
    count = 0
    strand1.chars.each.with_index{ |char,i| count += 1 if strand2[i] != char }
    count
  end