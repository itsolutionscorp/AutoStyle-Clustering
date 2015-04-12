class Hamming
  def compute(s1, s2)
    i = 0
    count = 0
    while i < s1.length do
      count += 1 if s1[i] != s2[i]
      i += 1
    end

    count
  end
end
