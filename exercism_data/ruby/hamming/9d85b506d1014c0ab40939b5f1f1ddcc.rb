class Hamming
  
  def self.compute strand_1, strand_2
    self.new(strand_1, strand_2).distance
  end

  def initialize(strand_1, strand_2)
    @strand_1 = strand_1
    @strand_2 = strand_2
  end

  def distance
    pairs.inject(0) do |result, (first_char,second_char)| 
      result += (first_char <=> second_char).abs
    end
  end
  
  private

  def pairs
    @strand_1.chars.zip(@strand_2.chars).each_with_object([]) do |pair,successive_character_pairs|
      successive_character_pairs << pair unless pair.include?(nil)
    end
  end

end
