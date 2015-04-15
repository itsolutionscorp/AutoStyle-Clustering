class Hamming

  def self.compute(strand_one, strand_two)
    hammes = 0
    [strand_one.length, strand_two.length].min.times do |a|
      if strand_one.chars[a] != strand_two.chars[a]
        hammes += 1
      end
    end
    hammes
  end

end
