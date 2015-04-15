class DNA

  NUCLEOTID = %w{A C G T}

  def initialize chain
    validate_chain! chain
    @chain = chain
  end

  def count nucleotid
    validate_nucleotid! nucleotid
    @chain.count(nucleotid)
  end

  def nucleotide_counts
    NUCLEOTID.each_with_object({}) do |nucleotid, acc|
      acc[nucleotid] = count nucleotid
    end
  end

  private

  def validate_chain! chain
    chain.chars.uniq.each { |char| validate_nucleotid! char }
  end

  def validate_nucleotid! nucleotid
    unless NUCLEOTID.include?(nucleotid)
      raise ArgumentError, 'is not a nucleotid'
    end
  end
end
