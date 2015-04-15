class DNA
  DNA_NUCLEOTIDS = %w{A T C G}
  RNA_NUCLEOTIDS = %w{A C G U}
  NUCLEOTIDS = DNA_NUCLEOTIDS | RNA_NUCLEOTIDS

  def initialize(input)
    @input = input.split('')
    base = Hash[DNA_NUCLEOTIDS.map { |v| [v,0] }]
    @nucleotide_counts = @input.each_with_object(base) do |nucleotid,memo| 
      memo[nucleotid] += 1
    end
  end

  def count(nucleotide)
    raise ArgumentError unless NUCLEOTIDS.include?(nucleotide)
    nucleotide_counts.fetch(nucleotide,0)
  end

  attr_reader :nucleotide_counts

  private
  attr_reader :counts
end
