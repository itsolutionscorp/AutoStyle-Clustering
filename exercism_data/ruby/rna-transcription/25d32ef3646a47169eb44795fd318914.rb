class Complement
  DNA_RNA = {
    'A' => 'U',
    'C' => 'G',
    'T' => 'A',
    'G' => 'C'
  }

  RNA_DNA = DNA_RNA.invert

  def self.of_dna(dna)
    dna.chars.map do |c| 
      raise ArgumentError if c == "U"
      DNA_RNA[c]
    end
    .join
  end

  def self.of_rna(rna)
    rna.chars.map do |c| 
      raise ArgumentError if c == "T"
      RNA_DNA[c]
    end
    .join
  end
end
