class Complement
   DNA_COMPLEMENT_RULES = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
   RNA_COMPLEMENT_RULES = {'G' => 'C', 'C' => 'G', 'U' => 'A', 'A' => 'T'}

   def self.of_dna(dna_strand)
      complement(dna_strand.chars, DNA_COMPLEMENT_RULES).join
   end

   def self.of_rna(rna_strand)
      complement(rna_strand.chars, RNA_COMPLEMENT_RULES).join
   end

   private

   def self.complement(strand, ruleset)
     strand.map {|base| ruleset[base]}
   end

end
