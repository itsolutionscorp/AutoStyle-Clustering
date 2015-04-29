module Nucleotide
  def self.from_dna(dna_string)
    Dna.new(dna_string)
  end

  class Dna
    def initialize(dna_string)
      raise ArgumentError if dna_string =~ /[^AGCT]/
      @dna_string = dna_string
    end

    def count(nucleotide)
      dna_string.gsub(/[^#{nucleotide}]/, '').length
    end

    def histogram
      default_count = { 'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0 }

      dna_string.each_char.with_object(default_count) do |nucleotide, count|
        count[nucleotide] += 1
      end
    end

    private

    attr_reader :dna_string
  end
end
