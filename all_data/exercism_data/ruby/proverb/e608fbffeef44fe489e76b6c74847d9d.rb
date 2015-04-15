require 'pry'
class Proverb
  def initialize(*words, qualifier: nil)
    @words, @qualifier = words, qualifier
  end

  def to_s
    @words.each_cons(2).map{ |wants| verse(*wants) }.join + envoi
  end

  private

  def verse(want1, want2)
    "For want of a #{want1} the #{want2} was lost.\n"
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
