class Complement

  DNA_RNA_RELATION = { 
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }
  RNA_DNA_RELATION = DNA_RNA_RELATION.invert

  class << self
    def of_dna strand
      new( strand, DNA_RNA_RELATION ).get_complement 
    end

    def of_rna strand
      new( strand, RNA_DNA_RELATION ).get_complement 
    end
  end

  attr_reader :strand, :relation_hash

  def initialize strand, relation_hash
    @strand        = strand
    @relation_hash = relation_hash
  end

  def get_complement
    strand.chars.map{ |nucleotide| relation_hash[ nucleotide ]}.join
  end

end
