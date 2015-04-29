class Complement
  class << self

    NUCLEOTIDE_MAP = {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }

    def of_dna(chain)
      transcribe(chain, NUCLEOTIDE_MAP)
    end

    def of_rna(chain)
      transcribe(chain, NUCLEOTIDE_MAP.invert)
    end

    private

    def transcribe(chain, nucleotide_map)
      chain.chars.map { |nucleotide| nucleotide_map[nucleotide] }.join
    end

  end
end
