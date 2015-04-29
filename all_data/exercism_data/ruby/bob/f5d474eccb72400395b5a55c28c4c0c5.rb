class Bob
  def hey(greeting_text)
    greeting = Greeting.new(greeting_text)

    if greeting.nothing?
      'Fine. Be that way!'
    elsif greeting.yelling?
      'Woah, chill out!'
    elsif greeting.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Greeting
  attr_reader :text

  def initialize(text)
    @text = text
  end

  def nothing?
    text.nil? or text.empty?
  end

  def question?
    text.end_with?('?')
  end

  def yelling?
    text == text.upcase
  end
end
