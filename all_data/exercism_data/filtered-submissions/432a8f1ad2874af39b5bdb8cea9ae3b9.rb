def compute a,b
    return 0 if a == b
    differences = 0
    a.chars.each_with_index do |e,i|
      differences += 1 if e != b[i]
    end
    differences
  end