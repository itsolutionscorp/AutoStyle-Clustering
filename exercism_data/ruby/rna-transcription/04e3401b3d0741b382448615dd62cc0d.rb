class Complement
    @dna_to_rna = {
        A: 'U',
        T: 'A',
        C: 'G',
        G: 'C',
    }

    def self.of_dna (c)
        final = ''
        c.each_byte { |char| final << @dna_to_rna[char.chr.to_sym] }
        final
    end

    def self.of_rna (c)
        @rna_to_dna = Hash.new
        @dna_to_rna.each { |k, v| @rna_to_dna[v.to_sym] = k.to_s }

        final = ''
        c.each_byte { |char| final << @rna_to_dna[char.chr.to_sym] }
        final
    end
end
