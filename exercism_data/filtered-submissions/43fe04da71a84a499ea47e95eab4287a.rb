class Hamming
  def compute(str1, str2)
    score = 0
    a1 = str1.split(//)
    a2 = str2.split(//)
    a1.each_with_index do |l,i|
      if a1[i] == a2[i]
        score += 0
      else
        score += 1
      end
    end
    score
  end
end
