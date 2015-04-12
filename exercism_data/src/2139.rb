class Hamming
  def compute(s1, s2)
    hamming = 0

    # lenth of chars to compare, shortest length of the two strands
    min = s1.size > s2.size ? s2.size : s1.size

    for i in 0...min
      hamming += 1 if s1[i] != s2[i]
    end

    hamming
  end
end
