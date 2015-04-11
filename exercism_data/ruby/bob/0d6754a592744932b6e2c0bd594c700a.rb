class Bob
  def hey(text)
    sentence = Message.new(text)

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

class Message
  attr_reader :text

  def initialize text
    @text = text
  end

  def shouting?
    all_caps? && at_least_one_letter_present?
  end

  def question?
    text.end_with? '?'
  end

  def silence?
    text.strip.empty?
  end

  private

  def all_caps?
    text.upcase == text
  end

  def at_least_one_letter_present?
    text.match(/[A-z]/)
  end
end
