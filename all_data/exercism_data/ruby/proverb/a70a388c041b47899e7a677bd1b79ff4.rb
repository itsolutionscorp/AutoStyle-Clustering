class Proverb
  VERSE = "For want of a %s the %s was lost."
  LAST_VERSE = "And all for the want of a %s."

  def initialize(*words, qualifier: nil)
    @words = words
    @qualifier = qualifier
  end

  def to_s
    [].tap do |out|
      @words.each_cons(2).map do |a, b|
        out << VERSE % [a, b]
      end
      out << LAST_VERSE % (@qualifier.nil? ? @words.first : "#{@qualifier} #{@words.first}")
    end.join("\n")
  end
end
