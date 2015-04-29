class Complement
  class << self

    CONVERSION_TABLE = [
      ["C", "G"],
      ["G", "C"],
      ["T", "A"],
      ["A", "U"]
    ]

    def of_dna strand
      transcribe(strand, :dna, :rna)
    end

    def of_rna strand
      transcribe(strand, :rna, :dna)
    end

    private

    def transcribe strand, *args
      strand.chars.inject("") do |new_strand, nucleotide|
        new_strand << complement_for(nucleotide, *args)
      end
    end

    def complement_for nucleotide, subject, complement
      complementing_nucleotide_for_subject do |dna, rna|
        eval(complement.to_s) if nucleotide == eval(subject.to_s)
      end
    end

    def complementing_nucleotide_for_subject
      CONVERSION_TABLE.each do |dna, rna|
        value = yield(dna, rna)
        return value if value
      end
    end
  end
end
