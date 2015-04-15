module Complement
 def self.of_dna(dna)
   DNA.new(dna).complement
 end

 def self.of_rna(rna)
   RNA.new(rna).complement
 end

 class Base
   DNA_MAP = 'GCTA'
   RNA_MAP = 'CGAU'
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
     string.tr(*char_parity)
   end
   
   def char_parity
     [DNA_MAP, RNA_MAP]
   end
 end

 class DNA < Base; end
 class RNA < Base
   private

   def char_parity
     [RNA_MAP, DNA_MAP]
   end
 end
end 
