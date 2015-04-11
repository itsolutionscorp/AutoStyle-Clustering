class Complement
   DNA_COMPLEMENT_RULES = Hash['G', 'C', 'C', 'G', 'T', 'A', 'A', 'U']
   RNA_COMPLEMENT_RULES = Hash['G', 'C', 'C', 'G', 'U', 'A', 'A', 'T']

   def self.of_dna(dna_strand)
      rna_complement = complement(dna_strand.chars, DNA_COMPLEMENT_RULES)
      return rna_complement
   end

   def self.of_rna(rna_strand)
      rna_complement = complement(rna_strand.chars, RNA_COMPLEMENT_RULES)
      return rna_complement
   end

   private

   def self.complement(strand, ruleset)
     strand.map {|base| ruleset[base]}.join
   end

end
