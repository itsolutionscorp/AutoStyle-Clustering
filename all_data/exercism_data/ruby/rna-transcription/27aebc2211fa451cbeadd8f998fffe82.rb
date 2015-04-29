class Complement
  RNA_COMPLEMENTS = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }
  DNA_COMPLEMENTS = RNA_COMPLEMENTS.invert

  def self.of_dna(rna)
    compare_with(rna, RNA_COMPLEMENTS)
  end

  def self.of_rna(dna)
    compare_with(dna, DNA_COMPLEMENTS)
  end

  private

  def self.compare_with(rna_or_dna, complements)
    rna_or_dna.split('').inject([]) do |results, cytosine|
      results << complements[cytosine]
    end.join()
  end
end
