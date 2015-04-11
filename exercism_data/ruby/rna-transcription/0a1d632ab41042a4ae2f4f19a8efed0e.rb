class Complement
  DNA_COMPLEMENTS = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }
  RNA_COMPLEMENTS = { 'C' => 'G', 'G' => 'C', 'U' => 'A', 'A' => 'T' }

  def self.of_dna(nucleotides)
    complement(nucleotides, DNA_COMPLEMENTS)
  end

  def self.of_rna(nucleotides)
    complement(nucleotides, RNA_COMPLEMENTS)
  end

  private

  def self.complement(nucleotides, complements)
    nucleotides.each_char.map do |c|
      if complements.key?(c)
        complements[c]
      else
        raise ArgumentError
      end
    end.join
  end
end
