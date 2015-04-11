class Hamming

  def self.compute(strand_one, strand_two)
    hammes = 0
    max(strand_one.length, strand_two.length).times do |a|
      if strand_one.chars[a] != strand_two.chars[a]
        hammes += 1
      end
    end
    hammes
  end

  def self.max(strand_one, strand_two)
    if strand_one >= strand_two
      strand_two
    else
      strand_one
    end
  end

end
