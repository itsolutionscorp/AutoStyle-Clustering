class Hamming
  
  def self.compute strand_1, strand_2
    self.new(strand_1, strand_2).distance
  end

  def initialize(strand_1, strand_2)
    @strand_1 = strand_1
    @strand_2 = strand_2
  end

  def distance
    pairs.count { |(first_char,second_char)| 
      first_char != second_char 
    }
  end
  
  private

  def pairs
    @strand_1.chars.zip(@strand_2.chars).take_while { |pair| !pair.any?(&:nil?) }
  end

end
