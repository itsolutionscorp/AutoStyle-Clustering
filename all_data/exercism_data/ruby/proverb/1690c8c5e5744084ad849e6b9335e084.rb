class Proverb

  attr_reader :chain, :qualifier
  def initialize(*parms)
    options = parms.last.is_a?(Hash) ? parms.pop : {}
    @chain = parms
    @qualifier = options.fetch :qualifier, nil
  end

  def to_s
    proverb = ""
    chain.each_cons(2) do |cause, effect|
      proverb << "For want of a #{cause} the #{effect} was lost.\n"
    end
    proverb << "And all for the want of a #{qualifier_string}#{first_cause}."
  end

  def qualifier_string
    qualifier ? "#{qualifier} " : ""
  end

  def first_cause
    chain.first
  end
end
