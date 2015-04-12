class Hamming
  def compute(s1, s2)
    count = 0

    0.upto(s1.length) do |index|
      count += 1 if s1[index] != s2[index]
    end

    return count
  end
end
