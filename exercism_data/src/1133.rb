def compute(first,second)
    count = 0
    first.chars.map.with_index do |c,i|
      count += 1 if c != second.chars[i]
    end
    count
  end