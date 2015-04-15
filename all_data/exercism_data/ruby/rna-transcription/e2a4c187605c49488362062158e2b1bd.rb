class Complement
  DNA_TO_RNA = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
  RNA_TO_DNA = {'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A'}

  def self.of_dna(rna)
    rna.chars.map{|c|
      DNA_TO_RNA[c]
    }.join
  end

  def self.of_rna(dna)
    dna.chars.map{|c|
      RNA_TO_DNA[c]
    }.join
  end
end
