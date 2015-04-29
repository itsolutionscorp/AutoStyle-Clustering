class Complement

  def self.of_dna(dna_seq)
    rna_complementary_base = {"A" => "U", "T" => "A", "G" => "C", "C" => "G"}
    rna_seq = ''
    for i in (0...dna_seq.length)
      rna_seq += rna_complementary_base[dna_seq[i]]
    end
    return rna_seq
  end

  def self.of_rna(rna_seq)
    dna_complementary_base = {"A" => "T", "U" => "A", "G" => "C", "C" => "G"}
    dna_seq = ''
    for i in (0...rna_seq.length)
      dna_seq += dna_complementary_base[rna_seq[i]]
    end
    return dna_seq
  end

end
