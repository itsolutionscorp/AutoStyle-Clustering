class Complement
  class << self
    def of_dna(string)
      map_by :dna, string
    end

    def of_rna(string)
      map_by :rna, string
    end

    private

    def map_by(key, string)
      string.chars.map {|char| complements[key].fetch(char) }.join
    end

    def complements
      { dna: { "T" => "A", "A" => "U", "G" => "C", "C" => "G" },
        rna: { "C" => "G", "G" => "C", "U" => "A", "A" => "T" }}
    end
  end
end
