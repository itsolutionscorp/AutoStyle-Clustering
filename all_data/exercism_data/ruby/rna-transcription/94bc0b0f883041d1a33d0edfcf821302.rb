class Complement
  DNA_COMPLEMENTS = {
    'T' => 'A',
    'A' => 'U',
    'C' => 'G',
    'G' => 'C'
  }

  RNA_COMPLEMENTS = {
    'A' => 'T',
    'U' => 'A',
    'C' => 'G',
    'G' => 'C'
  }

  def self.of_dna(dna)
    complementary_strand = ""

    dna.each_char do |base|
      complementary_strand += DNA_COMPLEMENTS[base]
    end

    complementary_strand
  end

  def self.of_rna(rna)
    complementary_strand = ""

    rna.each_char do |base|
      complementary_strand += RNA_COMPLEMENTS[base]
    end

    complementary_strand
  end
end
