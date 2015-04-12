def compute(a,b)
    i = 0
    a = a <=> b ? a.chars.take(b.length) : a.chars
    b = b.chars

    a.zip(b).each do |first, second|
      if first != second
        i += 1
      end
    end
    return i
  end