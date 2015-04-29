require 'set'

module Utils
  module_function

  def transform(enum, hash = {}, &block)
    enum.each_with_object(hash) { |e, acc|
      acc[e] = block.call(e)
    }
  end
end

class DNA
  include Utils

  def initialize(string)
    @nucleotides = map_codes_to_nucleotides(string.chars)
  end

  def count(nucleotide_code)
    @nucleotides.count(Nucleotide[nucleotide_code])
  end

  def nucleotide_counts
    transform(@nucleotides.map(&:code).uniq, frequencies_hash) {
      |code| count(code)
    }
  end

  private

  def frequencies_hash
    transform(Nucleotide::DNA_CODES) { 0 }
  end

  def map_codes_to_nucleotides(codes)
    codes.map(&Nucleotide.method(:[])).tap do |nucleotides|
      fail ArgumentError unless nucleotides.all?(&:dna?)
    end
  end

  class Nucleotide
    extend Utils

    COMMON_CODES      = %w(A C G).to_set.freeze
    DNA_CODES         = (COMMON_CODES | %w(T)).freeze
    RNA_CODES         = (COMMON_CODES | %w(U)).freeze
    VALID_CODES       = (DNA_CODES | RNA_CODES).freeze

    def self.[](code)
      valid_nucleotides.fetch(code) {
        fail ArgumentError, 'Invalid nucleotide'
      }
    end

    attr_reader :code

    def initialize(code)
      @code = code.freeze
    end

    def dna?
      DNA_CODES.include? code
    end

    def rna?
      RNA_CODES.include? code
    end

    def <=>(*args)
      code.<=>(*args)
    end

    private

    def self.valid_nucleotides
      @valid_nucleotides ||= transform(VALID_CODES) { |code| new(code) }
    end
  end
end
