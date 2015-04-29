class Complement
  
  DNA = ['G', 'C', 'T', 'A']
  RNA = ['C', 'G', 'A', 'U']
  
  def self.of_dna(dna_strand)
    dna_comps = Hash[DNA.zip RNA]
    rna_comp = []
    dna_strand.each_char { |x| rna_comp << dna_comps[x] }
    rna_comp.join
  end

  def self.of_rna(rna_strand)
    rna_comps = Hash[RNA.zip DNA]
    dna_comp = []
    rna_strand.each_char { |x| dna_comp << rna_comps[x] }
    dna_comp.join
  end
end
