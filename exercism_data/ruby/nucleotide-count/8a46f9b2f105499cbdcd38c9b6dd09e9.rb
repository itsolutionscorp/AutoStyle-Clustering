module Enumerable
  def transform(hash = {}, &block)
    each_with_object(hash) { |e, acc|
      acc[Array(e).first] = block.call(e)
    }
  end
end

require 'set'

class DNA
  def initialize(string)
    @nucleotides = codes_to_nucleotides(string.chars)

    validate_nucleotides! @nucleotides
  end

  def count(nucleotide_code)
    nucleotides.count(Nucleotide[nucleotide_code])
  end

  def nucleotide_counts
    nucleotides_codes.uniq.transform(frequencies_hash) { |code|
      count(code)
    }
  end

  private

  attr_reader :nucleotides

  def nucleotides_codes
    nucleotides.map(&:code)
  end

  def frequencies_hash
    Nucleotide::DNA_CODES.transform { 0 }
  end

  def codes_to_nucleotides(codes)
    codes.map(&Nucleotide.method(:[]))
  end

  def validate_nucleotides!(nucleotides)
    unless nucleotides.all?(&:dna?)
      fail ArgumentError, 'Non-DNA nucleotide passed'
    end
  end
end

class Nucleotide
  DNA_CODES         = %w(A C G T).to_set.freeze
  RNA_CODES         = %w(A C G U).to_set.freeze
  VALID_CODES       = (DNA_CODES | RNA_CODES).freeze

  private_class_method :new

  def self.[](code)
    VALID_NUCLEOTIDES.fetch(code) { fail ArgumentError, 'Invalid nucleotide' }
  end

  attr_reader :code

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

  def initialize(code)
    @code = code.freeze
  end

  VALID_NUCLEOTIDES = VALID_CODES.transform { |code| new(code) }
end
