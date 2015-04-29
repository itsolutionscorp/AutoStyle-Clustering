class Complement
  DNA_TO_RNA = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }
  RNA_TO_DNA = DNA_TO_RNA.invert

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
      DNA_TO_RNA.fetch(nucleotide) { raise ArgumentError }
    end

    def to_dna(nucleotide)
      RNA_TO_DNA.fetch(nucleotide) { raise ArgumentError }
    end
  end
end
