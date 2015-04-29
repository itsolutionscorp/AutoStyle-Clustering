class Proverb
  def initialize *words
    if words.last.kind_of? Hash
      @qualifier = words.last[:qualifier] + ' '
      @words = words[0..-2].reverse
    else
      @qualifier = ''
      @words = words.reverse
    end
  end

  def to_s
    verses.inject(:+)
  end

  def verses
    @words.reverse
          .each_cons(2)
          .map(&method(:verse)) +
      last_verse
  end

  def verse(words)
    cause, effect = words
    "For want of a #{cause} the #{effect} was lost.\n"
  end

  def last_verse
    ["And all for the want of a #{@qualifier}#{@words.last}."]
  end
end
