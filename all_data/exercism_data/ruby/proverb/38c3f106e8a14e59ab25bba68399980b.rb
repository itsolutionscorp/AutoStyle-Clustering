class Proverb
  def initialize(*chain, qualifier: nil)
    @chain = chain
    @qualified = [qualifier, chain.first].compact.join(' ')
  end

  def to_s
    @chain.each_cons(2).map do |first, second|
      "For want of a #{first} the #{second} was lost.\n"
    end.join << "And all for the want of a #{@qualified}."
  end
end
