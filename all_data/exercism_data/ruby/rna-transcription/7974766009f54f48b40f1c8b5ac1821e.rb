class Complement

  def self.of_dna(strand)
  	rna_complements = { G: 'C', C: 'G', T: 'A', A: 'U' }
  	self.split_and_replace strand, rna_complements
  end

  def self.of_rna(strand)
  	dna_complements = { C: 'G', G: 'C', A: 'T', U: 'A' }
  	self.split_and_replace strand, dna_complements
  end

  def self.split_and_replace(strand, complements)
  	new_strand = strand.split('').each { |i| i.replace complements[i.to_sym] }
    new_strand.join
  end

end
