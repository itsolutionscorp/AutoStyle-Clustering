class SumOfMultiples
  def self.to(limit)
    self.new.to(limit)
  end

  def initialize(*args)
    @multiples = args.empty? ? [3,5] : args
  end

  def to(limit)
    @multiples.map{|base| multiply_to(base, limit)}.flatten.uniq.reduce(&:+)
  end

  private
  def multiply_to(base, limit)
    return [0] if (limit-1)/base < 1
    (1..((limit-1)/base).floor).map{|multiplyer| base * multiplyer}
  end
end
