class Proverb
  def initialize(*causes)
    if causes.last.class == Hash
      @clause = causes.pop[:qualifier] + " "
    end
    @causes = causes
    @root_cause = causes.first
  end

  def to_s
    proverb = couplets.map do |couplet|
      to_proverb(couplet)
    end
    proverb.join("\n")
  end

  def couplets
    effects = @causes.clone.rotate
    @causes.zip(effects)
  end

  def to_proverb(couplet)
    cause, effect = couplet
    if effect == @root_cause
      and_all(effect)
    else
      for_want(cause, effect)
    end
  end

  def for_want(cause, effect)
    "For want of a #{cause} the #{effect} was lost."
  end

  def and_all(cause)
    "And all for the want of a #{@clause}#{cause}."
  end
end
