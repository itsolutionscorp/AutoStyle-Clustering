class Proverb
  def initialize(*causes, qualifier: nil)
    @causes = causes
    @clause = qualifier + " " if qualifier
  end

  def to_s
    proverb = stanzas
    proverb << ending
    proverb.join("\n")
  end

  def stanzas 
    @causes.each_cons(2).map do |pair|
      cause, effect = pair
      "For want of a #{cause} the #{effect} was lost."
    end
  end

  def ending
    "And all for the want of a #{@clause}#{@causes.first}."
  end
end
