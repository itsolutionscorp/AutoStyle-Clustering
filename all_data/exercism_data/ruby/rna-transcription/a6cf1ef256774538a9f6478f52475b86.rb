##RNA/DNA Complement (exercism.io)
##John Youngblood
##1/26/2015
class Complement
  def self.of_dna(dna)
    dna.gsub(/[GCTA]/, { 'G' => 'C', 'C' => 'G', 'A' => 'U', 'T' => 'A'})
  end
  def self.of_rna(rna)
    rna.gsub(/[GCAU]/, { 'G' => 'C', 'C' => 'G', 'U' => 'A', 'A' => 'T'})
  end
end
