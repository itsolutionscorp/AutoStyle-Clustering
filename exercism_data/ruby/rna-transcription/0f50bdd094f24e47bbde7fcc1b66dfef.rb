class Complement

  attr_reader :strand, :type

  DNA_NUCLEOTIDES = /[^BD-FH-SU-Z]/
  RNA_NUCLEOTIDES = /[^BD-FH-TV-Z]/

  DNA_TO_RNA_TRANSLATION = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  RNA_TO_DNA_TRANSLATION = {
    'C' => 'G',
    'G' => 'C',
    'A' => 'T',
    'U' => 'A'
  }

  def self.of_dna(dna)
    new(dna, :dna).convert
  end

  def self.of_rna(rna)
    new(rna, :rna).convert
  end

  def initialize(strand, type)
    @strand = strand
    @type = type
  end

  def convert
    raise ArgumentError unless valid_strand?

    strand.chars.map{ |nucleotide| translate_strand(nucleotide) }.join
  end

  private

  def valid_strand?
    nucleotides = type == :dna ? DNA_NUCLEOTIDES : RNA_NUCLEOTIDES
    !!(nucleotides =~ strand)
  end

  def translate_strand(nucleotide)
    translation = type == :dna ? DNA_TO_RNA_TRANSLATION : RNA_TO_DNA_TRANSLATION
    translation[nucleotide]
  end

end
