class Complement
    DNA2RNA = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }
    RNA2DNA = DNA2RNA.invert

    def self.of_dna(dna)
        convert(dna, DNA2RNA)
    end

    def self.of_rna(rna)
        convert(rna, RNA2DNA)
    end

    def self.convert(string, rules)
        string.chars.map{ |ch| rules[ch] }.join('')
    end 
    private_class_method :convert
end
