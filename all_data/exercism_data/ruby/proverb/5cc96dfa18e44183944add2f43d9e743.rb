class Proverb
  def initialize(*nouns, **options)
    @nouns = nouns
    @qualifier = options[:qualifier]
  end

  def to_s
    mid_lines + final_line
  end

  private

  def mid_lines
    @nouns.each_cons(2).map { |first_noun, next_noun|
      "For want of a #{first_noun} the #{next_noun} was lost."
    }.join("\n")
  end

  def final_line
    "\nAnd all for the want of a #{final_noun_phrase}."
  end

  def final_noun_phrase
    "#{@qualifier} #{@nouns.first}".strip
  end
end
