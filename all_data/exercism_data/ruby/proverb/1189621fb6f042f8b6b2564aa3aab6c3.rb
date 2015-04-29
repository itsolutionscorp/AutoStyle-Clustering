class Proverb
  attr_reader :chain, :qualifier
  def initialize(*chain, qualifier: "")
    @chain = chain
    @qualifier = qualifier
  end

  def to_s
    chain_of_events + conclusion
  end

  private

  def chain_of_events
    pair.map do |first, last|
      "For want of a #{first} the #{last} was lost."
    end.join("\n")
  end

  def conclusion
    "\nAnd all for the want of a #{add_on}#{chain.first}."
  end

  def pair
    chain.each_cons(2)
  end

  def add_on
    "#{qualifier} " if qualifier != ""
  end
end
