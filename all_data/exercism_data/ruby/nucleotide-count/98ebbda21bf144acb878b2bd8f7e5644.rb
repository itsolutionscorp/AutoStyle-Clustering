class DNA < String
  DNA_NUCLEOTIDES = ['A','T','C','G']
  ALL_NUCLEOTIDES = ['A','T','C','G','U']

  def nucleotide_counts
    counts = Hash.new { |hash,key| hash[key] = self.count(key) }
    DNA_NUCLEOTIDES.each { |nucleotide| counts[nucleotide] }
    return counts
  end

  def count nucleotide
    raise ArgumentError unless ALL_NUCLEOTIDES.include? nucleotide
    super
  end
end
