class Proverb

  def initialize(*words)
    if words.last.is_a?(Hash)
      @qualifier = words.pop[:qualifier]
    end
    @words = words
  end

  def to_s
    @words.each_cons(2).map { |word1, word2|
      "For want of a #{word1} the #{word2} was lost.\n"
    }.join + last_line
  end

  def last_line
    qualifier = "#{@qualifier} " if @qualifier
    "And all for the want of a #{qualifier}#{@words.first}."
  end

end
