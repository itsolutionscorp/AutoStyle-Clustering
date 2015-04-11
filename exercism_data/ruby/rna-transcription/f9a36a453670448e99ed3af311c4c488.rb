class Complement

  KEY = {'G' => 'C',
         'C' => 'G',
         'T' => 'A',
         'A' => 'U'}

  def self.of_dna(strand)
    rna = ''
    strand.chars.each do |char|
      rna += KEY[char]
    end
    rna
  end

  def self.of_rna(strand)
    dna = ''
    strand.chars.each do |char|
      dna += KEY.invert[char]
    end
    dna
  end
end
