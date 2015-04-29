require 'pry'
class Proverb
  def initialize(*words, qualifier: nil)
    @words, @qualifier = words, qualifier
  end

  def to_s
    response = ""
    @words.each_cons(2){ |wanted_items| response += verse(*wanted_items) }
    response + envoi
  end

  private

  def verse(word1, word2)
    "For want of a #{word1} the #{word2} was lost.\n"
  end

  def envoi
    "And all for the want of a#{qualifier} #{first_word}."
  end

  def first_word
    @words[0]
  end

  def qualifier
    " " + @qualifier if @qualifier
  end
end
