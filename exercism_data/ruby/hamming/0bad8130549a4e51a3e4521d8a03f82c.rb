class Hamming
  def self.compute(a,b)
    i = 0

    if (a <=> b) == 1
      a = a.chars.take(b.length)
    else
      a = a.chars
    end

    b = b.chars

    a.zip(b).each do |first, second|
      if first != second
        i += 1
      end
    end
    return i
  end
end
