# Module containing functions to map DNA strands to their RNA counterparts and
# vice versa.
module Complement
    @@dna_to_rna = {'G' => 'C',
                    'C' => 'G',
                    'T' => 'A',
                    'A' => 'U'}

    @@rna_to_dna = @@dna_to_rna.invert

    # Map the DNA strand to the matching RNA.
    def self.of_dna(dna_strand)
        match(dna_strand, @@dna_to_rna)
    end

    # Map the RNA strand to the matching DNA.
    def self.of_rna(rna_strand)
        match(strand, @@rna_to_dna)
    end

    def self.match(strand, mapping)
        strand.chars.map { |nucleotide| mapping[nucleotide] }.join
    end
end
