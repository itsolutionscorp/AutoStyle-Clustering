class Complement
  class TranscriptionMap
    def self.from(nucleotide, to:)
      find_pair(nucleotide, to: to)
        .find { |k, v| k == to }
        .last
    end

    def self.pairs
      [{dna: "C", rna: "G"},
       {dna: "G", rna: "C"},
       {dna: "T", rna: "A"},
       {dna: "A", rna: "U"}]
    end

    def self.find_pair(nucleotide, to:)
      pairs.find do |pair|
        pair.value?(nucleotide) && pair[to] != nucleotide
      end
    end
  end

  def self.of_dna strand
    transcribe strand, to: :rna
  end

  def self.of_rna strand
    transcribe strand, to: :dna
  end

  def self.transcribe(strand, to:)
    strand.chars
      .map { |nucleotide| TranscriptionMap.from(nucleotide, to: to) }
      .join
  end

end
