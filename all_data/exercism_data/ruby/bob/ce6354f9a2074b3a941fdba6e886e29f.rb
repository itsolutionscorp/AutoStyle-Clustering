class Speech

  attr_reader :text

  def initialize(text)
    @text = text
  end

  def shouting?
    !nothing? && text.upcase == text
  end

  def nothing?
    text.strip.empty?
  end

  def question?
    text.end_with?('?')
  end

end

class Bob
  def hey(text)
    speech = Speech.new(text)

    if speech.shouting?
      'Woah, chill out!'
    elsif speech.question?
      'Sure.'
    elsif speech.nothing?
      'Fine. Be that way!'
    else 
      'Whatever.'
    end
  end
end
