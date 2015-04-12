class Hamming
  def compute(s1, s2)
    if s1 == s2
      0
    else
      s1_a, s2_a = []
      s1_a = s1.split(/(a-zA-Z)*/)
      s2_a = s2.split(/(a-zA-Z)*/)
      distance = 0
      s1_a.each_with_index do |it, i|
        distance += 1 if s2_a[i] != it
      end

      distance
    end
  end
end
