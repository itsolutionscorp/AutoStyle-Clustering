class Proverb

  def initialize(*words, **kwargs)
    @words = words
    @qualifier = kwargs[:qualifier]
  end

  def to_s
    lines = @words.each_cons(2).map do |a, b|
      "For want of a #{a} the #{b} was lost."
    end
    lines.push("And all for the want of a #{qualified_first_word}.").join("\n")
  end

  private

  def qualified_first_word
    [@qualifier, @words[0]].compact.join(" ")
  end

end
