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
      @to = args.fetch(:to, @from)
    end

    def transcribe
      sequence.tr(pattern(from, :base), pattern(to, :complement))
    end

    private

    def pattern(acid, group)
      ACID_PATTERNS.fetch(acid).fetch(group)
    end

    ACID_PATTERNS = {
      dna: { base: 'CGAT', complement: 'GCTA' },
      rna: { base: 'CGAU', complement: 'GCUA' }
    }

    attr_reader :sequence, :from, :to
  end
  
end
