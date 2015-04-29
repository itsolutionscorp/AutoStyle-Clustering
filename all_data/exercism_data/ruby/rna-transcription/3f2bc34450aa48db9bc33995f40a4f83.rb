class Complement
  COMPLEMENTS = [
    ['G', 'C'],
    ['C', 'G',],
    ['T', 'A',],
    ['A', 'U' ]
  ]

  class << self
    def of_dna(strand)
      complements(strand, :dna)
    end

    def of_rna(strand)
      complements(strand, :rna)
    end
  end

  private

  def self.complements(strand, type)
    meth, index = case type
                  when :dna
                    [:assoc, 1]
                  when :rna
                    [:rassoc, 0]
                  else
                    raise ArgumentError, "Strand type must be either :dna or :rna"
                  end
    strand.chars.map { |nucleotide|
      COMPLEMENTS.public_send(meth, nucleotide)[index]
    }.join('')
  end
end
