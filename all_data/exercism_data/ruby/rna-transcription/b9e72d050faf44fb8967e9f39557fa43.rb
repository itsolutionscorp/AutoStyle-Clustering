class Complement < Struct.new(:sequence, :complements)
  DNA_COMPLEMENTS = { 'G' => 'C',
                      'C' => 'G',
                      'T' => 'A',
                      'A' => 'U' }

  def self.of_dna(dna)
    new(dna, DNA_COMPLEMENTS).perform
  end

  def self.of_rna(rna)
    new(rna, DNA_COMPLEMENTS.invert).perform
  end

  def perform
    sequence.tr("#{complements.keys}", "#{complements.values}")
  end
end
