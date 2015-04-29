class Proverb
  def initialize(*words)
    @word, qualifier, last_word = '', get_qualifier(words), 'And all for the want of a'

    words.each_cons(2) { |list| @word += "For want of a #{list[0]} the #{list[1]} was lost.\n" }
    @word += "#{last_word} #{qualifier}#{words.first}."
  end

  def to_s
    @word.to_s
  end

private
  def get_qualifier(input)
    (input.pop[:qualifier] + ' ' if input.last.is_a?(Hash)) || ''
  end
end
