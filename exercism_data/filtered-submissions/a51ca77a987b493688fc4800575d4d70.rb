class Hamming
  def compute(s1, s2)
    count = 0
    length = [s1.length, s2.length].min

    0.upto(length) do |index|
      count += 1 if s1[index] != s2[index]
    end

    return count
  end
end
