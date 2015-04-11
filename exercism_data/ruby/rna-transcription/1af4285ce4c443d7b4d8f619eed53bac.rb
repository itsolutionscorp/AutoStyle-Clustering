class Complement
    @table = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }
    def self.of_dna(dna)
        transcribe dna, @table
    end

    def self.of_rna(rna)
        transcribe rna, @table.invert
    end

    private
    def self.transcribe(xna, table)
        xna.chars.map { |ch| table[ch] }.join
    end

end
