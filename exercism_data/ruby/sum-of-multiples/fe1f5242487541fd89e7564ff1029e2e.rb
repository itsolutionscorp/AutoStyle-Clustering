require 'set'

class SumOfMultiples
  def initialize(*factors)
    @factors = if factors.empty?
                   [3, 5]
                 else
                   factors
                 end
  end

  attr_reader :factors

  def self.to(excluded_limit)
    new.to(excluded_limit)
  end

  def to(excluded_limit)
    multiples = Set.new
    factors.each do |factor|
      multiples_upto(factor, excluded_limit) do |multiple|
        multiples << multiple
      end
    end
    multiples.reduce(0, :+)
  end

  private

  def multiples_upto(factor, excluded_limit)
    (1...excluded_limit).each do |number|
      yield(number) if number % factor == 0
    end
  end
end
