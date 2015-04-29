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
    @causes.each_cons(2).map do |cause, effect|
      "For want of a #{cause} the #{effect} was lost."
    end
  end

  def ending
    "And all for the want of a #{@clause}#{@root_cause}."
  end
end
