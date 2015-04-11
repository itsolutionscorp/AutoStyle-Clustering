class Proverb
  attr_reader :chain, :options

  def initialize(*chain, **options)
    @chain = chain
    @options = options
  end

  def to_s
    [ cause_and_effect,
      conclusion ].join("\n")
  end

  private
  def cause_and_effect
    chain.each_cons(2).map do |cause, effect|
      consequence(cause, effect)
    end
  end

  def consequence(cause, effect)
    "For want of a %s the %s was lost." % [cause, effect]
  end

  def conclusion
    "And all for the want of a %s." % root_cause
  end

  def root_cause
    "%s%s" % [qualifier, chain.first]
  end

  def qualifier
    options[:qualifier] ?
      "%s " % options[:qualifier] : ""
  end
end
