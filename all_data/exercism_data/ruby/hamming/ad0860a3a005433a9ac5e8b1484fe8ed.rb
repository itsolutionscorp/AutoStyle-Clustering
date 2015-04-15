class Hamming
  def self.compute(strand_one, strand_two)
    Assessor.new(strand_one, strand_two).call
  end
end

class Assessor
  attr_reader :strand_one_nuclieotides, :strand_two_nuclieotides
  private :strand_one_nuclieotides, :strand_two_nuclieotides

  def initialize(strand_one, strand_two)
    @strand_one_nuclieotides = strand_one.chars
    @strand_two_nuclieotides = strand_two.chars
    @hamming = 0
  end

  def call
    strand_one_nuclieotides.each_with_index do |nucleotide, index|
      if nucleotides_equal?(nucleotide, strand_two_nuclieotides[index])
        #no op
      elsif nucleotide_at_strand_index_exists?(strand_two_nuclieotides[index])
        add_to_hamming
      end
    end

    @hamming
  end

  private

  def nucleotides_equal?(nucleotide_one, nucleotide_two)
    nucleotide_one == nucleotide_two
  end

  def nucleotide_at_strand_index_exists?(nucleotide)
    !nucleotide.nil?
  end

  def add_to_hamming
    @hamming += 1
  end
end
