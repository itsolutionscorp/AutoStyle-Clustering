def compute left, right
    shorter, longer = [left.chars, right.chars].sort_by(&:size)
    shorter.zip(longer).count { |pair| pair.first != pair.last } 
  end