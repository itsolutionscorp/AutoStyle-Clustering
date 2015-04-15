class Proverb
  def initialize(*words, qualifier: nil)
    @words = words
    @qualifier = qualifier
  end

  def to_s
    (repeated_lines << last_line).join("\n")
  end

  private

  def repeated_lines
    @words.each_cons(2).map do |first, second|
      "For want of a #{first} the #{second} was lost."
    end
  end

  def last_line
    qualified_first_word = [@qualifier, @words.first].compact.join(' ')
    "And all for the want of a #{qualified_first_word}."
  end
end
