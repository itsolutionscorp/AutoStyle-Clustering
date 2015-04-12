class Hamming
  def compute(str1, str2)
    count = str1.length - 1
    score = 0
    0.upto(count) do |num|
      score += 1 unless str1[num] == str2[num]
    end
    score
  end
end
