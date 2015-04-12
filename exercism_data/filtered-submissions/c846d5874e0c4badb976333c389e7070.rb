class Hamming

  def compute(str1, str2)
    score = 0

    str1.chars.each_with_index do |char, idx|
      score += 1 unless char.eql? str2.chars[idx]
    end
    score

  end
end
