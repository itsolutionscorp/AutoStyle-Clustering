class Proverb

  def initialize(*words, **kwargs)
    @consecutive_words = words.each_cons 2
    @qualified_first_word = [kwargs[:qualifier], words[0]].compact.join " "
  end

  def to_s
    lines = @consecutive_words.map do |a, b|
      "For want of a #{a} the #{b} was lost."
    end
    lines.push("And all for the want of a #{@qualified_first_word}.").join "\n"
  end

end
