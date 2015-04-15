require 'set'

class SumOfMultiples
  def self.to(n)
    new.to(n)
  end

  attr_reader :bases

  def initialize(*bases)
    @bases = bases.empty? ? [3, 5] : bases
  end

  def to(n)
    multiples(n).inject(0, &:+)
  end

  def multiples(to)
    bases.each_with_object(Set.new) do |base, multiples|
      multiple = base
      while multiple < to
        multiples << multiple
        multiple += base
      end
    end
  end
end
