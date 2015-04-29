class Complement
  class << self
    def of_dna(string)
      map_by COMPLEMENTS[:dna], string
    end

    def of_rna(string)
      map_by COMPLEMENTS[:rna], string
    end

    private

    def map_by(map, string)
      string.gsub(/\w/) {|char| map[char] }
    end

    COMPLEMENTS = { 
      dna: { "T" => "A", "A" => "U", "G" => "C", "C" => "G" },
      rna: { "C" => "G", "G" => "C", "U" => "A", "A" => "T" }
    }
  end
end
