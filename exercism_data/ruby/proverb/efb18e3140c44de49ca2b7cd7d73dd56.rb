class Proverb
  def initialize(*causes, qualifier: nil)
    @causes = causes
    @clause = qualifier + " " if qualifier
  end

  def to_s
    pairs = @causes.each_cons(2)

    proverb = pairs.map do |pair|
      cause, effect = pair
      "For want of a #{cause} the #{effect} was lost."
    end
    proverb <<  "And all for the want of a #{@clause}#{@causes.first}."
    proverb.join("\n")
  end

end
