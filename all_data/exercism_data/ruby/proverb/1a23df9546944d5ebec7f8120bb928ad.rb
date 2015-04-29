class Proverb
  def initialize(*words)
    optional = words.pop if words.last.is_a?(Hash)
    @word = ''
    words_size = words.size - 1

    words.size.times do |index|
      if (index === words_size)
        @word += optional ? "And all for the want of a #{optional[:qualifier]} #{words.first}." : "And all for the want of a #{words.first}."
      else
        @word += "For want of a #{words[i]} the #{words[i+1]} was lost.\n"
      end
    end
    @word
  end

  def to_s
    @word.to_s
  end
end
