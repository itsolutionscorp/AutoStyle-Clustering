# class to calculate RNA complement from DNA strand
class Complement
  def self.complements
    { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }
  end

  def self.of_dna(dna)
    transcribe(dna) { |n| complements[n] }
  end

  def self.of_rna(rna)
    transcribe(rna) { |n| complements.key n }
  end

  def self.transcribe(strand)
    strand.chars.map { |nucl| yield nucl }.join
  end
end
