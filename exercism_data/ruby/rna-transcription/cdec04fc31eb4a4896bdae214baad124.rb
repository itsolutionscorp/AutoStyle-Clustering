class Complement
  DNA_COMPLEMENTS = { 'C' => 'G', 'G' => 'C', 'T' => 'A', 'A' => 'U' }
  RNA_COMPLEMENTS = DNA_COMPLEMENTS.invert

  def self.of_dna(a)
    a.chars.map { |c| DNA_COMPLEMENTS[c] }.join
  end

  def self.of_rna(a)
    a.chars.map { |c| RNA_COMPLEMENTS[c] }.join
  end
end
