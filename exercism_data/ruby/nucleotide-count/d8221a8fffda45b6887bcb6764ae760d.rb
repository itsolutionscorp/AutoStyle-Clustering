class DNA
  SHARED_NUCLEOTIDE_TYPES = ["A", "C", "G"]
  DNA_NUCLEOTIDE_TYPE = SHARED_NUCLEOTIDE_TYPES + ["T"]
  RNA_NUCLEOTIDE_TYPE = SHARED_NUCLEOTIDE_TYPES + ["U"]

  attr_reader :nucleotide_counts

  def initialize(dna_chain)
    parse_chain(dna_chain)
  end

  def count(type)
    raise ArgumentError unless (DNA_NUCLEOTIDE_TYPE | RNA_NUCLEOTIDE_TYPE).include?(type)
    @nucleotide_counts[type] || 0
  end

  private
  def parse_chain(dna_chain)
    @nucleotide_counts = DNA_NUCLEOTIDE_TYPE.each_with_object({}) { |item, obj| obj[item] = dna_chain.count(item) }
    raise ArgumentError if (dna_chain.length > get_all_count)
  end

  def get_all_count
    @nucleotide_counts.values.inject { |sum, item| sum + item}
  end
end
