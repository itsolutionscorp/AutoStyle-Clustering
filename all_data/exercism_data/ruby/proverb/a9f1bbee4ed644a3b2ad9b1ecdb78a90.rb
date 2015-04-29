class Proverb
  attr_reader :chain, :qualifier

  def initialize(*chain)
    @chain = chain
    if optional_qualifier_present?
      @qualifier = chain.pop
    end
  end

  def optional_qualifier_present?
    chain.last.is_a?(Hash)
  end

  def  to_s
    if qualifier
      verses.join + "And all for the want of a #{qualifier[:qualifier]} #{chain[0]}."
    else
      verses.join + "And all for the want of a #{chain[0]}."
    end
  end

  def verses
    chain.each_cons(2).map do |cause, effect|
      "For want of a #{cause} " + "the #{effect} was lost.\n"
    end
  end
end
