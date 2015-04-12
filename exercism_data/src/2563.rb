def compute (a, b)
    distance = 0

    matches =  a.chars.zip(b.chars).map { |x, y| x == y || x.nil? || y.nil? }
    
    return matches.select {|x| !x}.count
  end