class Complement
  def self.of_dna(dna_strand)
    NucleotideStrandTranslator.new(DnaToRnaTranslation.new).translate(dna_strand)
  end

  def self.of_rna(rna_strand)
    NucleotideStrandTranslator.new(RnaToDnaTranslation.new).translate(rna_strand)
  end
end

class NucleotideStrandTranslator
  def initialize(translation)
    @translation = translation 
  end

  def translate(source_strand)
    translated_nucleotides_of(source_strand).join 
  end

  private
  attr_reader :translation

  def translated_nucleotides_of(strand)
    nucleotides_within(strand).map do |nucleotide|
      translation.translate(nucleotide) 
    end
  end

  def nucleotides_within(strand)
    strand.chars
  end
end

class RnaToDnaTranslation
  def translate(nucleotide)
    case nucleotide
      when "C" then "G"
      when "G" then "C"
      when "U" then "A"
      when "A" then "T"
    end
  end
end

class DnaToRnaTranslation
  def translate(nucleotide)
    case nucleotide
      when "C" then "G"
      when "G" then "C"
      when "T" then "A"
      when "A" then "U"
    end
  end
end
