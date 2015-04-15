class Complement
  @@dna_conversions = { 'G'=>'C', 'C'=>'G', 'T'=>'A', 'A'=>'U' }
  @@rna_conversions = { 'C'=>'G', 'G'=>'C', 'A'=>'T', 'U'=>'A' }

  def self.of_dna(rna_strain)
    new_strain = ''
    rna_strain.split('').each { |s| new_strain << @@dna_conversions[s] }
    new_strain
  end

  def self.of_rna(dna_strain)
    new_strain = ''
    dna_strain.split('').each { |s| new_strain << @@rna_conversions[s] }
    new_strain
  end
end
