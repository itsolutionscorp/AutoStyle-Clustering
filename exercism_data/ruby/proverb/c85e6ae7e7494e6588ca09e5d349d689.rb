class Proverb

  def initialize(*words, **kwargs)
    @consecutive_words = words[0..-2].zip words[1..-1]
    @qualified_first_word = [kwargs[:qualifier], words[0]].compact.join " "
  end

  def to_s
    lines = @consecutive_words.map do |a, b|
      "For want of a #{a} the #{b} was lost."
    end
    lines.push("And all for the want of a #{@qualified_first_word}.").join "\n"
  end

end
