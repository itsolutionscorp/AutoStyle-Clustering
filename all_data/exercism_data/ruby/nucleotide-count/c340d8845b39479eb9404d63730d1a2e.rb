NUCLEOTIDES = %w{A C G T U}

class DNA

   DNAS = NUCLEOTIDES - [ "U" ]

  def initialize(dna)
    raise ArgumentError unless (split(dna).uniq - DNAS).empty?
    @dna = dna
  end

  def count(dna)
    raise ArgumentError unless NUCLEOTIDES.include?(dna)
    split(@dna).count(dna)
  end

  def nucleotide_counts
    #DNAS.inject({}) { |memo, n| memo.merge( n => count(n)) }
    Hash[ DNAS.map{ |n| [ n, count(n)] } ]
  end

  private 

  def split(dna)
    dna.to_s.split('')
  end
end
