class Hamming
  
  def self.compute dna_sequence, second_dna_sequence
    self.new(dna_sequence, second_dna_sequence).inject(0) do |result, (first_char,second_char)| 
      result += (first_char <=> second_char).abs
    end
  end

  def initialize(first_sequence, second_sequence)
    @first_sequence = first_sequence
    @second_sequence = second_sequence
  end

  def inject accumulator, &block
    pairs.inject(accumulator,&block)
  end
  
  private

  def pairs
    @first_sequence.chars.zip(@second_sequence.chars).each_with_object([]) do |pair,successive_character_pairs|
      successive_character_pairs << pair unless pair.include?(nil)
    end
  end

end
