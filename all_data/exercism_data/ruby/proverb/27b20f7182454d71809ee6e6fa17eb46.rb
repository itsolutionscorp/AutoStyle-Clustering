class Proverb
  def initialize(*words, **kwargs)
    @words = words
    @qualifier = kwargs[:qualifier]
  end

  def to_s
    (lines << lament).join("\n")
  end

  private

  def lines
    @words.each_cons(2).map do |needed, needer|
      "For want of a #{needed} the #{needer} was lost."
    end
  end

  def lament
    "And all for the want of a #{qualified_first_word}."
  end

  def qualified_first_word
    [@qualifier, @words.first].compact.join(' ')
  end
end
