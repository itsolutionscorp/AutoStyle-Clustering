class Proverb
  def initialize(*words, qualifier: nil)
    @words = words
    @qualifier = qualifier
  end

  def to_s
    (repeated_lines << last_line).join("\n")
  end

  def repeated_lines
    @words.each_cons(2).map do |first, second|
      "For want of a #{first} the #{second} was lost."
    end
  end

  def last_line
    optional_qualifier_with_space = ''
    optional_qualifier_with_space = @qualifier + ' ' if @qualifier
    "And all for the want of a #{optional_qualifier_with_space}#{@words.first}."
  end
end
