class Hamming

  def compute(s1, s2)
    difference = 0
    if s1.length >= s2.length
      s2.length.times do |index|
        difference += 1 if s1[index] != s2[index]
      end
    else
      s1.length.times do |index|
        difference += 1 if s2[index] != s1[index]
      end
    end
    difference
  end
end
