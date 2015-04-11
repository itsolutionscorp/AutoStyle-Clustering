class DNAStrand

  attr_reader :dna_strand

  def initialize(dna_strand)
    @dna_strand = dna_strand
  end

  def nucleotide_points(&block)
    dna_strand.chars.each_index(&block)
  end

  def exceeds_bound?(point)
    point >= dna_strand.size
  end
end

class DNAClone

  attr_reader :original_dna_strand, :cloned_dna_strand, :hamming_distance

  def initialize(original_dna_strand, cloned_dna_strand)
    @original_dna_strand = DNAStrand.new original_dna_strand
    @cloned_dna_strand   = DNAStrand.new cloned_dna_strand
    @hamming_distance    = 0
  end

  def nucleotide_points(&block)
    original_dna_strand.nucleotide_points(&block)
  end

  def exceeds_bound?(point)
    original_dna_strand.exceeds_bound?(point) || cloned_dna_strand.exceeds_bound?(point)
  end

  def compute_hamming_distance_on_point(point)
    @hamming_distance += 1 if mutated_on_point? point
  end

  private

  def mutated_on_point?(point)
    original_dna_strand.dna_strand[point] != cloned_dna_strand.dna_strand[point]
  end
end

class Hamming

  def self.compute(original_dna_strand, cloned_dna_strand)
    dna_clone = DNAClone.new original_dna_strand, cloned_dna_strand

    dna_clone.nucleotide_points do |point|
      if dna_clone.exceeds_bound? point
        break
      else
        dna_clone.compute_hamming_distance_on_point point
      end
    end

    dna_clone.hamming_distance
  end
end
