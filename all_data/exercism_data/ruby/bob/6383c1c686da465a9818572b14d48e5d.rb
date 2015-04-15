class Bob
  def initialize(phrase = Phrase)
    @phrase_sentiment_tester = phrase
  end

  def hey(str)
    phrase = phrase_sentiment_tester.new(str)

    if phrase.empty?
      'Fine. Be that way!'
    elsif phrase.yelling?
      'Woah, chill out!'
    elsif phrase.question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  attr_reader :phrase_sentiment_tester
end

class Phrase
  attr_reader :str

  ALL_UPPERCASE = /^[A-Z]+$/
  NON_WORD_DIGITS_SPACES = /[\W\d\s]/

  def initialize(str)
    @str = str
  end

  def yelling?
    ALL_UPPERCASE.match(str.gsub(NON_WORD_DIGITS_SPACES, ''))
  end

  def question?
    str.end_with?('?')
  end

  def empty?
    str.strip.empty?
  end
end
