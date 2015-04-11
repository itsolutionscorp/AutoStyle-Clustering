module Complement

  def self.of_dna(sequence)
    AcidMaker.new(sequence, from: :dna, to: :rna).transcribe
  end

  def self.of_rna(sequence)
    AcidMaker.new(sequence, from: :rna, to: :dna).transcribe
  end

  class AcidMaker
    def initialize(sequence, args={})
      @sequence = sequence.strip.upcase
      @from = args.fetch(:from)
      @to = args.fetch(:to)
    end

    def transcribe
      sequence.tr(pattern(from), pattern(to))
    end

    private

    def pattern(acid)
      AcidMaker.const_get("#{acid.to_s.upcase}_PATTERN")
    end

    DNA_PATTERN = 'GCTA'
    RNA_PATTERN = 'CGAU'

    attr_reader :sequence, :from, :to
  end
  
end
