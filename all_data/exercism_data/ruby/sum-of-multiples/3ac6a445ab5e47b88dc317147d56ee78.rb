require 'set'

class SumOfMultiples

  DEFAULT_BASES = [ 3, 5 ]

  def self.to limit
    new( *DEFAULT_BASES ).to( limit )
  end

  attr_reader :bases

  def initialize *bases
    @bases = bases
  end

  def to limit
    all_multiples_until( limit ).reduce( 0, :+ )
  end

private

  def all_multiples_until limit
    bases.reduce Set.new do |multiples, base|
      multiples + multiples_until( limit, base )
    end
  end

  def multiples_until limit, base
    multiples_of( base ).take_while { |multiple| multiple < limit }
  end

  def multiples_of base
    ( 1..Float::INFINITY ).lazy.map { |index| base * index }
  end

end
