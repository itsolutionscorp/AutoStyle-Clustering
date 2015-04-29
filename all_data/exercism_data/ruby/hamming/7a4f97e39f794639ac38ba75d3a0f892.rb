class Hamming
  def self.compute(arg1, arg2)
      count = 0
      score = 0
    while (count < arg1.length) do
      score += 1 if arg1[count] != arg2[count]
      count += 1
    end
    score
  end
end
