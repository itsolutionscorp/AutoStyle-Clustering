class Hamming
  
  def self.compute strand_1, strand_2
    self.new(strand_1, strand_2).distance
  end

  def initialize(strand_1, strand_2)
    @strand_1 = strand_1
    @strand_2 = strand_2
  end

  def distance
    pairs.count { |pair| pair.mutation? }
  end
  
  private

  def pairs
    @strand_1.chars.zip(@strand_2.chars).take_while { |pair| !pair.any?(&:nil?) }.map { |(first_char,second_char)| Pair.new(first_char,second_char)}
  end
  
  Pair = Struct.new(:first, :second) do
    
    def mutation?
      first != second 
    end
    
  end

end
