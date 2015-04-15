class Proverb
  def initialize *chain, qualifier: nil
    @chain = chain
    @qualifier = qualifier
  end

  def to_s
    chain.each_cons(2).map do |a, b|
      "For want of a #{a} the #{b} was lost.\n"
    end.join + "And all for the want of a #{[qualifier, chain.first].compact.join(' ')}."
  end

  private
  attr_reader :chain, :qualifier
end
