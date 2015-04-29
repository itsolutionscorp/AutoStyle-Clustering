class SumOfMultiples
  def initialize(*factors)
    @factors = factors.sort
  end

  def to(limit)
    return 0 if limit <= @factors.first
    sum list_of_multiples limit
  end

  def self.to(limit)
    new(3, 5).to limit
  end

  private

  def list_of_multiples_of(factor, limit)
    (1..(limit.to_i / factor)).map { |e| e * factor }
  end

  def list_of_multiples(limit)
    @factors.map { |factor| list_of_multiples_of factor, limit }
      .flatten.uniq.reject { |factor| factor == limit }
  end

  def sum(list)
    list.reduce(:+)
  end
end
