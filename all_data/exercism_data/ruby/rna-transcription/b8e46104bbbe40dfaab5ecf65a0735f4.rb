class Complement

  DNA_TO_RNA =
              { 'G' => 'C',
                'C' => 'G',
                'T' => 'A',
                'A' => 'U' }

  RNA_TO_DNA = DNA_TO_RNA.invert

  def self.of_dna(letter)
    rna = letter.gsub!(/[GCTA]/, DNA_TO_RNA )
    rna

  end

  def self.of_rna(letter)
    letter.gsub!(/[CGAU]/, RNA_TO_DNA )
  end
end
