class Complement

  DNA_TO_RNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  RNA_TO_DNA = DNA_TO_RNA.invert

  def self.of_dna(dna)
    rna = ""
    dna.each_char do |c|
      rna += DNA_TO_RNA[c]
    end
    rna
  end

  def self.of_rna(rna)
    dna = ""
    rna.each_char do |c|
      dna += RNA_TO_DNA[c]
    end
    dna
  end

end
