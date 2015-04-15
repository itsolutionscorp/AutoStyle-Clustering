class Complement 
  RNA_COMPLEMENTS = {"A" => "U",
                     "T" => "A",
                     "G" => "C",
                     "C" => "G"}

  DNA_COMPLEMENTS = {"A" => "T",
                     "U" => "A",
                     "G" => "C",
                     "C" => "G"}

  def self.of_dna(dna_strand)
    dna_strand.chars.map {|dna_base| complementary_rna_base(dna_base) }.join
  end

  def self.of_rna(rna_strand)
    rna_strand.chars.map {|rna_base| complementary_dna_base(rna_base) }.join
  end

  def self.complementary_rna_base(dna_base)
    complementary_base(dna_base, RNA_COMPLEMENTS)
  end

  def self.complementary_dna_base(rna_base)
    complementary_base(rna_base, DNA_COMPLEMENTS)
  end

  def self.complementary_base(base, complements_hash)
    complements_hash[base]
  end
end
