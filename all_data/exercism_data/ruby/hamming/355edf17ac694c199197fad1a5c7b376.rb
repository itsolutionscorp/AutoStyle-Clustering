class Hamming

  def self.compute(s1, s2)
    if s1.length >= s2.length
      compare_second_strand(s1,s2)
    else
      compare_first_strand(s1,s2)
    end
  end

  def self.compare_first_strand(s1,s2)
    difference = 0
    s1.length.times do |index|
      if s1[index] != s2[index]
        difference += 1
      end
    end
    difference
  end

  def self.compare_second_strand(s1, s2)
    difference = 0
    s2.length.times do |index|
      if s2[index] != s1[index]
        difference += 1
      end
    end
    difference
  end

end
