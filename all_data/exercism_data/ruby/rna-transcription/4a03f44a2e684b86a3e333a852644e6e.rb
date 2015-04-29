class Complement
  DNA_COMPLEMENTS = { 'C' => 'G', 'G' => 'C', 'T' => 'A', 'A' => 'U' }
  RNA_COMPLEMENTS = { 'C' => 'G', 'G' => 'C', 'U' => 'A', 'A' => 'T' }

  def self.of_dna(rna)
    sequence = rna.split(//)
    sequence.collect! do |t|
      fail ArgumentError unless DNA_COMPLEMENTS.include?(t)
      DNA_COMPLEMENTS[t]
    end
    sequence.join
  end

  def self.of_rna(dna)
    sequence = dna.split(//)
    sequence.collect! do |t|
      fail ArgumentError unless RNA_COMPLEMENTS.include?(t)
      RNA_COMPLEMENTS[t]
    end
    sequence.join
  end
end
