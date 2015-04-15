class Proverb

  attr_reader :chain, :qualifier
  def initialize(*parms)
    options = parms.last.is_a?(Hash) ? parms.pop : {}
    @chain = parms
    @qualifier = options.fetch :qualifier, nil
  end

  def to_s
    proverb = ""
    chain.each_cons(2) do |first, last|
      proverb << "For want of a #{first} the #{last} was lost.\n"
    end
    proverb << "And all for the want of a #{qualifier_string}nail."
  end

  def qualifier_string
    qualifier ? "#{qualifier} " : ""
  end
end
