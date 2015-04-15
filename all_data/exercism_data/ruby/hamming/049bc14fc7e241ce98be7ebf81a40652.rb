module Hamming
  
  def self.compute dna_sequence, second_dna_sequence
    Comparitor.new(dna_sequence, second_dna_sequence).pairs.inject(0) { |result, (a,b)| result += (a <=> b).abs }
  end
  
  private
  
  class Comparitor
    
    def initialize(first_sequence, second_sequence)
      @first_sequence = first_sequence
      @second_sequence = second_sequence
    end
   
    def pairs
      @first_sequence.chars.zip(@second_sequence.chars).delete_if { |pair| pair.any?(&:nil?)}
    end

  end

end
