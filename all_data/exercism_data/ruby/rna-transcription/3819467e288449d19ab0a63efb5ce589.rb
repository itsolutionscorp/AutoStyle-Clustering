class Complement

    @@dna_complements = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }
    @@rna_complements = { 'G' => 'C', 'C' => 'G', 'U' => 'A', 'A' => 'T' }
    
    def self.of_dna( dna )
      of_strand dna, @@dna_complements
    end

    def self.of_rna( rna )
      of_strand rna, @@rna_complements
    end

    private
      def self.of_strand( strand, complements )
        strand.chars.map { |nucleotide| complements[nucleotide] }.join
      end

end
