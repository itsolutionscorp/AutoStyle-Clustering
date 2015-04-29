class Complement

  DNA_TO_RNA = {
                  "G" => "C",
                  "C" => "G",
                  "T" => "A", 
                  "A" => "U" 
               }

  RNA_TO_DNA = DNA_TO_RNA.invert

  def self.of_dna(strand)
    strand_arr = strand.scan(/./)
    rna_strand = []
    strand_arr.each do |n|
      rna_strand << DNA_TO_RNA[n]
    end
    rna_strand.join("")
  end

  def self.of_rna(strand)
    strand_arr = strand.scan(/./)
    dna_strand = []
    strand_arr.each do |n|
      dna_strand << RNA_TO_DNA[n]
    end
    dna_strand.join("")
  end

end
