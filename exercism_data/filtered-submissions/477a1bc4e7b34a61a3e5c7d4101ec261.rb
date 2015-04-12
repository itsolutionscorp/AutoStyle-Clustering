class Hamming
  def compute(a, b)
    result= 0
    a = a.chars
    b = b.chars
    size = a.length-1
    for letter in 0..size
      if a[letter] != b[letter]
        result += 1
      end
    end
    return result
  end
end
