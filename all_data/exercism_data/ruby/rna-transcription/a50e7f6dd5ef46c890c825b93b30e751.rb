module Complement
 def self.of_dna(dna)
   DNA.new(dna).complement
 end

 def self.of_rna(rna)
   RNA.new(rna).complement
 end

 class Base
   CHAR_PARITY = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }
   attr_accessor :origin, :complement

   def initialize(origin)
     @origin = origin
   end

   def complement
     @complement ||= translate origin 
   end

   private

   def translate(string)
     return unless string
     string.split(//).collect { |char|  char_parity[char] || char }.join
   end
   
   def char_parity
     CHAR_PARITY
   end
 end

 class DNA < Base; end
 class RNA < Base
   private

   def char_parity
     Hash[super.to_a.collect(&:reverse)]
   end
 end
end 
