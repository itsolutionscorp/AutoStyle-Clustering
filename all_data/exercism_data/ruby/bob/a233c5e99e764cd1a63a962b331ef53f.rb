class Bob
  def hey phrase
    phrase = Phrase::Conversions.Phrase phrase

    if    phrase.shouting?          then 'Woah, chill out!'
    elsif phrase.asking_a_question? then 'Sure.'
    elsif phrase.silence?           then 'Fine. Be that way!'
    else                                 'Whatever.'
    end
  end
end

class Phrase
  attr_reader :text

  def initialize text
    @text = text
  end

  def shouting?
    text == text.upcase && text != text.downcase
  end

  def asking_a_question?
    text.end_with? '?'
  end

  def silence?
    text.strip == ''
  end

  module Conversions
    module_function

    def Phrase object
      case object
      when Phrase then object
      when String then Phrase.new object
      else fail "Cannot convert #{ object } into Phrase"
      end
    end
  end
end
