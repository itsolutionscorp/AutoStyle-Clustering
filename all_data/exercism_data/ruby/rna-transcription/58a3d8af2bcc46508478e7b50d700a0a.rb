class Complement
  attr_reader :strand

  COMPLEMENTS = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U"
  }

  def self.of_dna(string, klass = self)
    klass.new(string).transcribe
  end

  def self.of_rna(string)
    of_dna(string, RNA)
  end

  def initialize(string)
    @strand = string.each_char
  end

  def transcribe
    strand.map {|x| dictionary[x]}.join
  end

  private

    def dictionary
      COMPLEMENTS
    end
end

class RNA < Complement
  private

    def dictionary
      COMPLEMENTS.invert
    end
end
