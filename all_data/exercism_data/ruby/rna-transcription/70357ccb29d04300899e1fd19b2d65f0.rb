class Complement
  def self.of_dna(strand)
    DNA.new(strand).to_rna
  end

  def self.of_rna(strand)
    RNA.new(strand).to_dna
  end
end

class DNA
  attr_reader :strand

  def initialize(strand)
    @strand = strand
  end

  def to_rna
    rna_complement = ''

    strand_array.each do |nucleotide|
      rna_complement << transcribe(nucleotide)
    end

    rna_complement
  end

  private

  def strand_array
    strand.split(//)
  end

  def transcribe(nucleotide)
    case nucleotide
    when 'G' then 'C'
    when 'C' then 'G'
    when 'T' then 'A'
    when 'A' then 'U'
    end
  end
end

class RNA
  attr_reader :strand

  def initialize(strand)
    @strand = strand
  end

  def to_dna
    dna_complement = ''

    strand_array.each do |nucleotide|
      dna_complement << transcribe(nucleotide)
    end

    dna_complement
  end

  private

  def strand_array
    strand.split(//)
  end

  def transcribe(nucleotide)
    case nucleotide
    when 'G' then 'C'
    when 'C' then 'G'
    when 'U' then 'A'
    when 'A' then 'T'
    end
  end
end
