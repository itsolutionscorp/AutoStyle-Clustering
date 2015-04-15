class Complement
  def self.of_dna(dna)
    complements = {'G' => 'C',
                  'C' => 'G',
                  'T' => 'A',
                  'A' => 'U'}
    dna.chars.map {|char| complements[char]}.join
  end
  def self.of_rna(rna)
    complements = {'C' => 'G',
              'G' => 'C',
              'A' => 'T',
              'U' => 'A'}
    rna.chars.map {|char| complements[char]}.join          
  end
end
