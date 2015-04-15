class Complement
  COMPLEMENT_MAP = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  class << self
    def of_dna(dna)
      regex = Regexp.new COMPLEMENT_MAP.keys.join '|'
      dna.gsub regex, COMPLEMENT_MAP
    end

    def of_rna(rna)
      regex = Regexp.new COMPLEMENT_MAP.values.join '|'
      rna.gsub regex, COMPLEMENT_MAP.invert
    end
  end
end
