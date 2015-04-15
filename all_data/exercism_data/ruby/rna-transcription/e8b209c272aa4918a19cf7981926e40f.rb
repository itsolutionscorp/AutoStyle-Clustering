class Complement
  def self.dna_rna_rosetta
    {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }
  end
  def self.valid_dna_nucleotide(n)
    self.dna_rna_rosetta.keys.include? n
  end
  def self.valid_rna_nucleotide(n)
    self.dna_rna_rosetta.values.include? n
  end
  def self.of_dna(dna)
    dna.chars.map! { |i|
      if valid_dna_nucleotide(i)
       self.dna_rna_rosetta[i]
      else
        raise ArgumentError.new("Invalid Sequence")
      end
    }*""
  end
  def self.of_rna(rna)
    rna.chars.map! { |i|
      if valid_rna_nucleotide(i)
         i = self.dna_rna_rosetta.key(i)
      else
        raise ArgumentError.new("Invalid Sequence")
      end
    }*""
  end
end
