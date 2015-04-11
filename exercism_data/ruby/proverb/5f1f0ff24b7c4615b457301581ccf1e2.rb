class Proverb
  def initialize(*chain)
    @qualifier = chain.pop[:qualifier] if chain.last.is_a?(Hash)
    @chain = chain
  end

  def root_cause
    [@qualifier, @chain.first].compact.join(' ')
  end

  def to_s
    proverb = @chain.each_cons(2).map do |cause, effect|
      "For want of a #{cause} the #{effect} was lost."
    end
    proverb << "And all for the want of a #{root_cause}."
    proverb.join("\n")
  end
end
