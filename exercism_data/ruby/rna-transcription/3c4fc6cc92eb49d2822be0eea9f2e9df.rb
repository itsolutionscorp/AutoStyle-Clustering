class Complement
  class << self
    def of_dna(strand)
      strand.gsub(/[A-Z]/, map)
    end

    def of_rna(strand)
      strand.gsub(/[A-Z]/, map.invert)
    end

    private

    def map
      {
        'G' => 'C',
        'C' => 'G',
        'T' => 'A',
        'A' => 'U'
      }
    end
  end
end
