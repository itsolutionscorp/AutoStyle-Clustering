class Complement
  NUCLEOTIDES = {
    "dna" => %w[G C T A],
    "rna" => %w[C G A U]
  }
  
  attr_reader :source_type, :strand
  
  def initialize(source_type, strand)
    @source_type = source_type
    @strand = strand
  end
  
  def perform
    strand.gsub Regexp.union(NUCLEOTIDES[source_type]), Hash[NUCLEOTIDES.values_at(source_type, dest_type).transpose]
  end
  
  class << self
    def method_missing(method, *args)
      if /^of_(dna|rna)$/ =~ method
        new($1, *args).perform
      else
        super
      end
    end
  end
  
  private
  def dest_type
    { "dna" => "rna", "rna" => "dna" }[source_type]
  end
end
