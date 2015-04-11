class Proverb
  VERSE = "For want of a %s the %s was lost.\n"
  LAST_VERSE = "And all for the want of a %s."

  def initialize(*words, qualifier: nil)
    @words = words
    @qualifier = qualifier
  end

  def to_s
    consequences + ending
  end

  private
  def consequences
    @words.each_cons(2).map { |a, b| VERSE % [a, b] }.join
  end

  def ending
    LAST_VERSE % (@qualifier.nil? ? @words.first : "#{@qualifier} #{@words.first}")
  end
end
