class Proverb
  def initialize(*words)
    @word, qualifier = '', get_qualifier(words)

    (words.size - 1).times { |index| @word += "For want of a #{words[index]} the #{words[index+1]} was lost.\n" }

    @word += (qualifier) ? "And all for the want of a #{qualifier} #{words.first}." : "And all for the want of a #{words.first}."
  end

  def to_s
    @word.to_s
  end

private
  def get_qualifier(input)
    input.pop[:qualifier] if input.last.is_a?(Hash)
  end
end
