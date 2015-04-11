class Hamming
  def self.compute(s1, s2)
    distance = 0
    s1.each_char.with_index do |char, index|
      next unless s2[index]
      if char != s2[index]
        distance += 1
      end
    end
    distance
  end
end
