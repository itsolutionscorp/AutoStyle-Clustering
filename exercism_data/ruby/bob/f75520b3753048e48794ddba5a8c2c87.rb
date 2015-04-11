class Bob
  def hey(text)
    sentence = Sentence.new(text)

    case
    when sentence.shouting?
      'Woah, chill out!'
    when sentence.question?
      'Sure.'
    when sentence.silence?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end

class Sentence
  attr_reader :text

  def initialize text
    @text = text
  end

  def shouting?
    all_caps? && at_least_one_letter_present?
  end

  def question?
    last_character == '?'
  end

  def silence?
    only_spaces || empty_string
  end

  private

  def all_caps?
    text.upcase == text
  end

  def at_least_one_letter_present?
    text.match(/[A-z]/)
  end

  def last_character
    text[-1]
  end

  def empty_string
    text == ''
  end

  def only_spaces
    text =~ /^\s+$/
  end

end
