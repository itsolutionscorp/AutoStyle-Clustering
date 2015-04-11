class Complement
  COMPLEMENT_MAP = { "G" => "C", "C" => "G", "T" => "A", "A" => "U" }

  def self.of_dna(dna_strand) 
    create_complement_strand dna_strand, COMPLEMENT_MAP
  end

  def self.of_rna(rna_strand)
    create_complement_strand rna_strand, COMPLEMENT_MAP.invert
  end

  private

  def self.create_complement_strand(strand, complement_map)
    complement_strand = ""

    strand.each_char do |c|
      complement_strand << complement_map[c]
    end

    complement_strand
  end
end
