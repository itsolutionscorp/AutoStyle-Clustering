module Complement

  def self.of_dna(sequence)
    AcidMaker.new(sequence: sequence, from: :dna, to: :rna).transcribe
  end

  def self.of_rna(sequence)
    AcidMaker.new(sequence: sequence, from: :rna, to: :dna).transcribe
  end

  class AcidMaker
    def initialize(args={})
      @sequence = args.fetch(:sequence).strip.upcase.chars
      @from = args.fetch(:from)
      @to = args.fetch(:to)
    end

    def transcribe
      sequence.map do |nucleotide|
        complement_pattern[given_pattern.index(nucleotide)]
      end.join
    end

    private

    def given_pattern
      retrieve_pattern(from)
    end

    def complement_pattern
      retrieve_pattern(to)
    end

    DNA_PATTERN = ['G', 'C', 'T', 'A']
    RNA_PATTERN = ['C', 'G', 'A', 'U']

    def retrieve_pattern(acid)
      AcidMaker.const_get("#{acid.to_s.upcase}_PATTERN")
    end

    attr_reader :sequence, :from, :to
  end
  
end
