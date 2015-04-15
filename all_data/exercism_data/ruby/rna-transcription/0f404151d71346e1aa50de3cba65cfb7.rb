class Complement
    @@DNA_TO_RNA = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
    @@RNA_TO_DNA = @@DNA_TO_RNA.invert

    def self.of_rna(str)
        str.chars.map {|c| @@RNA_TO_DNA[c]}.join
    end

    def self.of_dna(str)
        str.chars.map {|c| @@DNA_TO_RNA[c]}.join
    end

end
