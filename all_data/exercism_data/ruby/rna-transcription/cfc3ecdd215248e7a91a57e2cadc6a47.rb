class Complement
  @@transcriptorToRNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'}
    
  @@transcriptorToDNA ={
    'G' => 'C',
    'C' => 'G',
    'U' => 'A',
    'A' => 'T'}
  
  def self.of_dna(nucleotide)
    rnaStrand = nucleotide.split(//).map {|n| @@transcriptorToRNA[n]}
    rnaStrand.join
  end
  
  def self.of_rna(nucleotide)
    dnaStrand = nucleotide.split(//).map {|n| @@transcriptorToDNA[n]}
    dnaStrand.join
  end
  
end
