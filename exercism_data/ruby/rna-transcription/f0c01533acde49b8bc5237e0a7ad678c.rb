class Complement
  PATTERN = ['CGTA', 'GCAU']

  class << self
    def of_dna(str)
      str.tr(*PATTERN)
    end

    def of_rna(str)
      str.tr(*PATTERN.reverse)
    end
  end
end
