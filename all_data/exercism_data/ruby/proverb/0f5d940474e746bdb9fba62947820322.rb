class Proverb
  attr_reader :words, :qualifier

  def initialize *words, qualifier: nil
    @words = words
    @qualifier = qualifier
  end

  def to_s
    arranged_words.join("\n") + last_line
  end

  private

  def arranged_words    
    words.each_cons(2).map { |a, b| "For want of a #{a} the #{b} was lost." }
  end

  def last_line
    qualified_word = [qualifier, words.first].compact.join(" ")
    "\nAnd all for the want of a #{qualified_word}."
  end

end
