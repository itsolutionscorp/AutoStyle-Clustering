require 'pry'

class DNA
  def initialize(string)
    @nucleotide_counts = count_nucleotides(string)
  end

  def count(symbol)
    nucleotide = AbstractNucleotide.create(symbol)
    nucleotide_counts.fetch(nucleotide, 0)
  end

  def nucleotide_counts
    @nucleotide_counts
  end

  private

  def count_nucleotides(string)
    counter = NucleotideCounter.new
    string.chars.each do |symbol|
      nucleotide = Nucleotide.create(symbol)
      counter.add(nucleotide)
    end
    counter.counts
  end
end

class DNA::NucleotideCounter
  def add(symbol)
    counts[symbol] += 1
  end

  def counts
    @counts ||= {}.tap do |hash|
      DNA::Nucleotide.valid_symbols.each do |symbol|
        hash[symbol] = 0
      end
    end
  end
end

class AbstractNucleotide
  def self.create(symbol)
    invalid = -> { raise ArgumentError, "#{symbol} is an invalid symbol" }
    valid_symbols.detect(invalid) { |s| s == symbol }
  end

  def self.valid_symbols
    %w{ A C G T U }
  end
end

class DNA::Nucleotide < AbstractNucleotide
  def self.valid_symbols
    %w{ A C G T }
  end
end
