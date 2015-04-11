class Complement
  DNA_TO_RNA = { G: 'C', C: 'G', T: 'A', A: 'U' }

  class << self

    def of_dna(strand)
      each_nucleotide(strand) do |nucleotide|
        to_rna(nucleotide)
      end
    end

    def of_rna(strand)
      each_nucleotide(strand) do |nucleotide|
        to_dna(nucleotide)
      end
    end

    private

    def each_nucleotide(strand)
      strand.chars.collect { |nucleotide| yield nucleotide }.join
    end

    def to_rna(nucleotide)
      DNA_TO_RNA.fetch nucleotide.to_sym
    rescue KeyError
      raise ArgumentError
    end

    def to_dna(nucleotide)
      if dna = DNA_TO_RNA.key(nucleotide)
        dna.to_s
      else
        raise ArgumentError
      end
    end
  end
end
