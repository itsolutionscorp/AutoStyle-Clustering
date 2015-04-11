# * `G` -> `C`
# * `C` -> `G`
# * `T` -> `A`
# * `A` -> `U`
class Complement
  RNA_COMPLEMENTS = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U"
  }

  DNA_COMPLEMENTS = {
    "C" => "G",
    "G" => "C",
    "A" => "T",
    "U" => "A"
  }

  def self.of_dna(dna_strand)
    rna_strand = []
    dna_strand.split('').each do |dna|
      rna_strand << RNA_COMPLEMENTS[dna]
    end
    return rna_strand.join('')
  end

  def self.of_rna(rna_strand)
    dna_strand = []
    rna_strand.split('').each do |rna|
      dna_strand << DNA_COMPLEMENTS[rna]
    end
    return dna_strand.join('')
  end

end
