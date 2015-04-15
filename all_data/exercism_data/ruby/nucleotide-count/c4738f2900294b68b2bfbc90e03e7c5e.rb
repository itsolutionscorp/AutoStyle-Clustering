require 'byebug'

class DNA
  NUCLEOTIDES = %w{A C G T U}
  DNA_NUCLEOTIDES = %w{A C G T}

  attr_accessor :string
  attr_accessor :nucleotide_counts

  def initialize(string)
    throw ArgumentError unless string.each_char.all? { |c| DNA_NUCLEOTIDES.include? c }

    self.nucleotide_counts = Hash[DNA_NUCLEOTIDES.map{ |n| [n, 0] }]
    self.string = parse(string)
  end

  def count(nucleotide)
    throw ArgumentError unless NUCLEOTIDES.include? nucleotide

    nucleotide_counts[nucleotide] || 0
  end


  protected
  def parse(string)
    string.each_char do |nucleotide|
      nucleotide_counts[nucleotide] += 1
    end
  end
end
