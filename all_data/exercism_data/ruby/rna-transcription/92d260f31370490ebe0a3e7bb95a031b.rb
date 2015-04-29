module Complement
  DNAtoRNA = { 'G'=>'C', 'C'=>'G', 'T'=>'A', 'A'=>'U' }
  RNAtoDNA = { 'C'=>'G', 'G'=>'C', 'U'=>'A', 'A'=>'T' }

  def self.of_dna(dna) 
    raise ArgumentError if dna.match(/[^#{DNAtoRNA.keys.join}]/)
    self.translate(DNAtoRNA, dna)
  end

  def self.of_rna(rna)
    raise ArgumentError if rna.match(/[^#{RNAtoDNA.keys.join}]/)
    self.translate(RNAtoDNA, rna)
  end

  def self.translate(table, strain)
    strain.each_char.map { |nuc| table[nuc] }.join
  end
end
