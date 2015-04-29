class Strand

  attr_accessor :strand

  def initialize(strand)
    @strand = strand
  end

  def complement
    nucleobases.map(&method(:complement_of)).join
  end

private

  def nucleobases
    strand.chars
  end

end

class RnaStrand < Strand

private

  def complement_of(nucleobase)
    case nucleobase
    when 'G' then 'C'
    when 'C' then 'G'
    when 'A' then 'T'
    when 'U' then 'A'
    end
  end

end

class DnaStrand < Strand

private

  def complement_of(nucleobase)
    case nucleobase
    when 'G' then 'C'
    when 'C' then 'G'
    when 'A' then 'U'
    when 'T' then 'A'
    end
  end

end

class Complement

  def self.of_dna(strand)
    DnaStrand.new(strand).complement
  end

  def self.of_rna(strand)
    RnaStrand.new(strand).complement
  end

end
