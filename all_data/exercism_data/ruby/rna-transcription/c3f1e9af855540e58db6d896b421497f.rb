class Complement
  DNA_TO_RNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(dna)
    dna.chars.reduce('') { |complement, nucleotide|
      complement += DNA_TO_RNA[nucleotide]
    }
  end

  def self.of_rna(rna)
    rna.chars.reduce('') { |complement, nucleotide|
      complement += DNA_TO_RNA.invert[nucleotide]
    }
  end
end
