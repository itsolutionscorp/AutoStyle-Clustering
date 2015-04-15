def compute d, _d
    h = 0
    d.each_char.each.with_index do |c, i|
      c != _d[i] and h += 1
      break if _d.size == i+1
    end
    h
  end