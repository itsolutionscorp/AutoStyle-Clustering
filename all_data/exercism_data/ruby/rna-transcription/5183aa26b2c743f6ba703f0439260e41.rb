class Complement
  COMPLEMENTS = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }

  def self.of_dna strand
    strand.chars.map do |c|
      raise ArgumentError unless COMPLEMENTS.has_key? c
      COMPLEMENTS.fetch(c)
    end.join('')
  end

  def self.of_rna strand
    strand.chars.map do |c|
      raise ArgumentError unless COMPLEMENTS.invert.has_key? c
      COMPLEMENTS.invert.fetch(c)
    end.join('')
  end
end
