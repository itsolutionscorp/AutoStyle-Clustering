class Complement
    @@dna2rna = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }
    @@rna2dna = Hash[@@dna2rna.map{ |key, val| [val, key] }]

    def self.of_dna(dna)
        return self.convert(dna, @@dna2rna)
    end

    def self.of_rna(rna)
        return self.convert(rna, @@rna2dna)
    end

    private

    def self.convert(string, rules)
        return string.chars.map{ |ch| rules[ch] }.join('')
    end 
end
