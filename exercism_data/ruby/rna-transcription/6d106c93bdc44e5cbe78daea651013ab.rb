class Complement
  def initialize(strand, translator)
    @strand = strand
    @translator = translator
  end

  def translate
    strand.chars.each_with_object("") do |nucleotide, new_strand|
      new_strand << translator.translate(nucleotide)
    end
  end

  class << self
    def of_dna(strand)
      new(strand, Translation::DNA.new).translate
    end

    def of_rna(strand)
      new(strand, Translation::RNA.new).translate
    end
  end

  private
  attr_reader :strand, :translator
end

module Translation
  class DNA
    TRANSLATIONS = {
      "C" => "G",
      "G" => "C",
      "T" => "A",
      "A" => "U",
      "U" => "A",
    }

    def translate(nucleotide)
      TRANSLATIONS[nucleotide]
    end
  end

  class RNA
    TRANSLATIONS = {
      "C" => "G",
      "G" => "C",
      "T" => "A",
      "A" => "T",
      "U" => "A",
    }

    def translate(nucleotide)
      TRANSLATIONS[nucleotide]
    end
  end
end
