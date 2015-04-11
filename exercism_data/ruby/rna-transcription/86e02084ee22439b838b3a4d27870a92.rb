class Complement
  CONVERSTIONS = { 'G'=>'C', 'C'=>'G', 'T'=>'A', 'A'=>'U' }

  def self.of_dna(rna_strain)
    new_strain = ''
    rna_strain.split('').each { |s| new_strain << CONVERSTIONS[s] }
    new_strain
  end

  def self.of_rna(dna_strain)
    new_strain = ''
    dna_strain.split('').each { |s| new_strain << CONVERSTIONS.invert[s] }
    new_strain
  end
end
