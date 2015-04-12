class Hamming

  def compute(s1, s2)
    differences = 0
    s1.chars.each_index do |i|
      differences += 1 if s1[i] != s2[i]
    end
    differences
  end

end
