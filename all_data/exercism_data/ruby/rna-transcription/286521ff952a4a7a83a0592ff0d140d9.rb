class Complement
  def self.of_dna(dna)
    DnaStrand.new(dna).to_rna.to_s
  end

  def self.of_rna(rna)
    RnaStrand.new(rna).to_dna.to_s
  end
end

class Strand
  attr_accessor :points

  def self.strand_point_class
    StrandPoint
  end

  def initialize(str)
    if str.respond_to? :chars
      self.points = str.chars.map { |char| self.class.strand_point_class.new(char) }
    else
      self.points = str
    end
  end

  def to_s
    points.map(&:char).join
  end
end

class DnaStrand < Strand
  def self.strand_point_class
    DnaStrandPoint
  end

  def to_rna
    RnaStrand.new points.map(&:to_rna)
  end
end

class RnaStrand < Strand
  def self.strand_point_class
    RnaStrandPoint
  end

  def to_dna
    DnaStrand.new points.map(&:to_dna)
  end
end

class StrandPoint
  DNA_NUCLEOTIDES = %w{G C T A}
  RNA_NUCLEOTIDES = %w{C G A U}

  attr_accessor :char

  def initialize(char)
    if self.class.my_valid_nucleotides
      fail "Invalid nucleotide #{char} for #{self.class.name}" unless self.class.my_valid_nucleotides.include?(char)
    end

    self.char = char
  end
end

class DnaStrandPoint < StrandPoint
  def self.my_valid_nucleotides
    DNA_NUCLEOTIDES
  end

  def to_rna
    RnaStrandPoint.new RNA_NUCLEOTIDES.at(DNA_NUCLEOTIDES.find_index(char))
  end
end

class RnaStrandPoint < StrandPoint
  def self.my_valid_nucleotides
    RNA_NUCLEOTIDES
  end

  def to_dna
    DnaStrandPoint.new DNA_NUCLEOTIDES.at(RNA_NUCLEOTIDES.find_index(char))
  end
end
