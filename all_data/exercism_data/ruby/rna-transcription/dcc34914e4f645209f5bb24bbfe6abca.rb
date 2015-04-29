class Complement
  def self.of_dna(dna_strand)
    DNAStrand.new(dna_strand).rna_complement
  end

  def self.of_rna(rna_strand)
    RNAStrand.new(rna_strand).dna_complement
  end
end

class DNAStrand
  attr_reader :strand 

  def initialize(strand)
    @strand = strand
  end

  def rna_complement
    strand.split(//).map do |nucleotide|
      nucleotide_converter.dna_nucleotide_to_rna(nucleotide)
    end.join
  end

  private
  def nucleotide_converter
    @nucleotide_converter ||= NucleotideConverter.new
  end
end

class RNAStrand
  attr_reader :strand 

  def initialize(strand)
    @strand = strand
  end

  def dna_complement
    strand.split(//).map do |nucleotide|
      nucleotide_converter.rna_nucleotide_to_dna(nucleotide)
    end.join
  end

  private
  def nucleotide_converter
    @nucleotide_converter ||= NucleotideConverter.new
  end
end

class NucleotideConverter
  def dna_nucleotide_to_rna(nucleotide)
    case nucleotide
      when 'G' then 'C'
      when 'C' then 'G'
      when 'T' then 'A'
      when 'A' then 'U'
    end
  end

  def rna_nucleotide_to_dna(nucleotide)
    case nucleotide
      when 'C' then 'G'
      when 'G' then 'C'
      when 'A' then 'T'
      when 'U' then 'A'
    end
  end
end
