class Hamming

  def self.compute(strand_one, strand_two)
    count = 0
    hammes = 0

    until count == distance(strand_one.chars.length, strand_two.chars.length)
      if strand_one.chars[count] != strand_two.chars[count]
        hammes += 1
        count += 1
      else
        count += 1
      end
    end
    hammes
  end

  def self.distance(strand_one, strand_two)
    if strand_one >= strand_two
      distance = strand_two
    else
      distance = strand_one
    end
    distance
  end

end
