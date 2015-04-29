class Complement
  TRANSLATION = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(dna)
    dna.chars.map do |c|
      raise ArgumentError unless TRANSLATION.has_key?(c)

      TRANSLATION[c]
    end.join
  end

  def self.of_rna(rna)
    rna.chars.map do |c|
      raise ArgumentError unless TRANSLATION.has_value?(c)

      TRANSLATION.key(c)
    end.join
  end
end
