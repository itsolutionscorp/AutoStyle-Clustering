require 'set'

class DNA
  def initialize(string)
    @nucleotides = map_codes_to_nucleotides(string.chars)
  end

  def count(nucleotide)
    @nucleotides.count(Nucleotide[nucleotide])
  end

  def nucleotide_counts
    @nucleotides.each_with_object(frequencies_hash) { |nucleotide, frequencies|
      frequencies[nucleotide.code] += 1
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

  def transform(enum, &block)
    enum.each_with_object({}) { |e, acc|
      acc[e] = block.call(*[e, acc])
    }
  end

  class Nucleotide
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
      @valid_nucleotides ||= VALID_CODES.each_with_object({}) { |code, hash|
        hash[code] = new(code)
      }
    end
  end

end
