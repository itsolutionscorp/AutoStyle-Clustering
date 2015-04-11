class DNA
  NUCLEOTIDES = %w{A T C G U}
  URACIL = 'U'

  def initialize(dns_string)
    @dns_string = dns_string
  end

  def count(nucleotide)
    raise ArgumentError unless NUCLEOTIDES.include?(nucleotide)
    @dns_string.scan(nucleotide).size
  end

  def nucleotide_counts
    nucleotides_witout_uracil.each_with_object({}) do |nucleotide, count|
      count[nucleotide] = count(nucleotide)
    end
  end

  private

  def nucleotides_witout_uracil
    NUCLEOTIDES - [URACIL]
  end
end
