module Complement
    
    DNA_LANG = 'GCTA'
    RNA_LANG = 'CGAU' 
    
    def self.of_dna( strand )
        strand.tr( DNA_LANG, RNA_LANG )
    end
    
    def self.of_rna( strand)
        strand.tr( RNA_LANG, DNA_LANG )
    end
    
end
