class Complement
  NUCLEOTIDES = {
                  'G' => 'C',
                  'C' => 'G',
                  'T' => 'A',
                  'A' => 'U'
                }

  def self.of_dna(sequence)
    sequence.chars.map { |e| NUCLEOTIDES[e] }.join
  end

  def self.of_rna(sequence)
    sequence.chars.map { |e| NUCLEOTIDES.key(e) }.join
  end
end
