class SumOfMultiples
  def self.to(limit)
    new(3, 5).to(limit)
  end

  def initialize(*factors)
    @factors = factors
  end

  def to(limit)
    chains = @factors.map { |n| chain_for(n, limit) }
    chains.flatten.uniq.reduce(:+)
  end

  private

  def chain_for(factor, limit)
    chain    = [0]
    multiple = factor

    while multiple < limit
      chain << multiple
      multiple += factor
    end

    chain
  end

end
