class Nucleotide
  attr_reader :name

  def initialize nucleotide_name
    @name = nucleotide_name
  end

  public

  def complement
    case @name
    when "A" then "U"
    when "C" then "G"
    when "T" then "A"
    when "G" then "C"
    end
  end
end

class DNA
  attr_reader :dna_strand

  def initialize strand
    @dna_strand = create_dna_from strand
  end

  public

  def transcribe_to_rna
    @dna_strand.collect! { |nucleotide| nucleotide.complement }.join().to_s
  end

  private

  def create_dna_from strand
    strand.split(//).collect! { |x| Nucleotide.new x }
  end
end

class Complement
  def self.of_dna strand
    DNA.new(strand).transcribe_to_rna
  end
end
