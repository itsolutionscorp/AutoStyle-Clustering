class Proverb
  attr_reader :words, :qualifier

  def initialize(*words, qualifier: nil)
    @words     = words
    @qualifier = qualifier
  end

  def to_s
    lines.join("\n") + closing_line
  end

  private

  def lines
    words.each_cons(2).map {|a, b| line(a, b) }
  end

  def line(a, b)
    %{For want of a #{a} the #{b} was lost.}
  end

  def closing_line
    "\nAnd all for the want of a #{qualified_first_word}."
  end

  def qualified_first_word
    [qualifier, words.first].compact.join(" ")
  end
end
