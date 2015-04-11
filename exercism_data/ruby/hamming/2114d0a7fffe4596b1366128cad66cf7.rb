class Hamming
  def self.compute(origin, mutation)
    new(origin, mutation).compute
  end

  def initialize(origin, mutation)
    @origin = origin
    @mutation = mutation
  end

  def compute
    mutated_pairs.length
  end

  private

  def mutated_pairs
    pairs.select { |pair| pair.uniq.length != 1 }
  end

  def pairs
    origin.zip(mutation).take(shortest)
  end

  def origin
    @origin.chars
  end

  def mutation
    @mutation.chars
  end

  def shortest
    [origin.length, mutation.length].min
  end
end
