# `G` -> `C`
# `C` -> `G`
# `T` -> `A`
# `A` -> `U`

class Complement
  @@rna_complements = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
  @@dna_complements = {'G' => 'C', 'C' => 'G', 'U' => 'A', 'A' => 'T'}
  
  def self.of_dna(dna)
    rna=""
    dna.each_char { |char| rna << @@rna_complements[char]}
    rna
  end

  def self.of_rna(rna)
    dna=""
    rna.each_char { |char| dna << @@dna_complements[char]}
    dna
  end

end
