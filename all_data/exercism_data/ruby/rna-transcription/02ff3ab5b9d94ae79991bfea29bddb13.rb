class Complement
    @@dna_comp = {'G'=>'C', 'C'=>'G', 'T'=>'A', 'A'=>'U'}
    @@rna_comp = {'G'=>'C', 'C'=>'G', 'U'=>'A', 'A'=>'T'}
    def self.of_dna(dna)
        cc = []
        dna.chars.each do |c|
             cc.push(@@dna_comp[c])
        end
        return cc.join()
    end

    def self.of_rna(rna)
       cc = []
        rna.chars.each do |c|
             cc.push(@@rna_comp[c])
        end
        return cc.join()
    end
end
