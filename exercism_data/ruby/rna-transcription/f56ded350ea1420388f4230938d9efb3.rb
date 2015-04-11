class Complement
  def self.of_dna(string)
    converter = Converter.new(map: :rna)
    converter.convert! string
  end

  def self.of_rna(string)
    converter = Converter.new(map: :dna)
    converter.convert! string
  end
end

class Converter
  class InvalidTypes < TypeError; end
  RNA = {
    "T" => "A",
    "G" => "C",
    "C" => "G",
    "A" => "U"
  }
  DNA = Hash[ RNA.to_a.map(&:reverse) ]
  def initialize(map: :rna)
    @map = case map
    when :rna
      RNA
    when :dna
      DNA
    else
      raise InvalidTypes.new("Invalid type #{map} requested.")
    end
  end

  def convert!(string)
    string.upcase.chars.map! do |nucleotide|
      @map[nucleotide]
    end.join("")
  end
end
