class Proverb
  def initialize(*keywords)
    options = keywords.last.is_a?(Hash) ? keywords.pop : {}

    @keywords = keywords
    @qualifier = options[:qualifier]
  end

  def to_s
    verses.to_a.join("\n")
  end

  private

  def verses
    Enumerator.new do |y|
      @keywords.each_cons(2).each do |w1, w2|
        y << "For want of a #{w1} the #{w2} was lost."
      end
      word = @keywords.first
      qualified_word = @qualifier ? "#{@qualifier} #{word}" : word
      y << "And all for the want of a #{qualified_word}."
    end
  end
end
