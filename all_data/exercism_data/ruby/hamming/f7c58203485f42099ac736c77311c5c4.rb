class Hamming
  def self.compute(strand_one, strand_two)
    gene_difference = 0

    smaller_dna, larger_dna = sort_dna_by_size DNA.new(strand_one), DNA.new(strand_two)

    smaller_dna.each_with_index do |gene, indx|
      gene_difference += 1 if gene != larger_dna.gene_at(indx)
    end

    gene_difference
  end

  private

  def self.sort_dna_by_size(dna_one, dna_two)
    return [dna_one, dna_two] if dna_one <= dna_two
    [dna_two, dna_one]
  end
end


class DNA
  attr_accessor :strand

  def initialize(strand)
    @strand = strand.split ""
  end

  def size
    @strand.size
  end

  def <=(dna)
    size <= dna.size
  end

  def gene_at(index)
    @strand[index]
  end

  def each_with_index(&blk)
    @strand.each_with_index(&blk)
  end
end
