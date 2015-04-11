class Complement
  BASE = {
    "C" => "G",
    "G" => "C",
    "U" => "A",
    "T" => "A",
  }

  DNA = BASE.merge "A" => "U"
  RNA = BASE.merge "A" => "T"

  class << self
    def of_dna strand
      of DNA, strand
    end

    def of_rna strand
      of RNA, strand
    end

    private

    def of type, strand
      strand.chars.map(&type.method(:fetch)).join
    end
  end

end
