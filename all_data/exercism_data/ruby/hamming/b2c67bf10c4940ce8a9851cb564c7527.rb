module Hamming
  
  def self.compute dna_sequence, second_dna_sequence
    Pairs.new(dna_sequence, second_dna_sequence).inject(0) do |result, (first_char,second_char)| 
      result += (first_char <=> second_char).abs
    end
  end
  
  private
  
  class Pairs
    
    def initialize(first_sequence, second_sequence)
      @first_sequence = first_sequence
      @second_sequence = second_sequence
    end
    
    def inject accumulator, &block
      pairs.inject(accumulator,&block)
    end
   
    def pairs
      @first_sequence.chars.zip(@second_sequence.chars).each_with_object([]) do |pair,successive_character_pairs|
        successive_character_pairs << pair unless pair.include?(nil)
      end
    end

  end

end
