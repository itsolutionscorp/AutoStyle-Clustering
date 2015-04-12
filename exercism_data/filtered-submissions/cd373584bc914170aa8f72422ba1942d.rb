class Hamming
  def compute(str0, str1)
    dist = 0
    i = 0

    while i < str0.size
      dist += 1 if str0[i] != str1[i]
      i += 1
    end

    return dist
  end
end
