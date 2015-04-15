class Proverb
  def initialize(*causes, qualifier: nil)
    @causes = causes
    @clause = qualifier + " " if qualifier
    @root_cause = causes.first
  end

  def to_s
    (stanzas << ending).join("\n")
  end

  def stanzas 
    @causes.each_cons(2).map { |cause, effect| stanza(cause, effect) }
  end

  def stanza(cause, effect)
    "For want of a #{cause} the #{effect} was lost."
  end

  def ending
    "And all for the want of a #{@clause}#{@root_cause}."
  end
end
