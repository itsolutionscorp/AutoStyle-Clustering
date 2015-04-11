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
    @nucleotides = map_codes_to_nucleotides(string.chars)
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

  def map_codes_to_nucleotides(codes)
    codes.map(&Nucleotide.method(:[])).tap do |nucleotides|
      fail ArgumentError unless nucleotides.all?(&:dna?)
    end
  end
end

class Nucleotide
  COMMON_CODES      = %w(A C G).to_set.freeze
  DNA_CODES         = (COMMON_CODES | %w(T)).freeze
  RNA_CODES         = (COMMON_CODES | %w(U)).freeze
  VALID_CODES       = (DNA_CODES | RNA_CODES).freeze

  private_class_method :new

  def self.[](code)
    valid_nucleotides.fetch(code) { fail ArgumentError, 'Invalid nucleotide' }
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

  def self.valid_nucleotides
    @valid_nucleotides ||= VALID_CODES.transform { |code| new(code) }
  end

  def initialize(code)
    @code = code.freeze
  end
end
