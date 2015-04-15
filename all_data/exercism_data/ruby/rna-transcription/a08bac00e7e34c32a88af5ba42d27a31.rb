class Complement

  DNA_TO_RNA = {'G' => 'C',
                'C' => 'G',
                'T' => 'A',
                'A' => 'U'}

  RNA_TO_DNA = DNA_TO_RNA.invert

  def self.of_dna(strand)
    rna = ''
    strand.chars.each do |char|
      rna += DNA_TO_RNA[char]
    end
    rna
  end

  def self.of_rna(strand)
    dna = ''
    strand.chars.each do |char|
      dna += RNA_TO_DNA[char]
    end
    dna
  end
end
