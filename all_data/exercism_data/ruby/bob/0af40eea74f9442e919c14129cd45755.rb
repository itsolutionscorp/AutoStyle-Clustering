class Bob

  RESPONSES = { question: 'Sure.',
                shout: 'Woah, chill out!',
                silent: 'Fine. Be that way!',
                unknown: 'Whatever.'}
  def hey(text)
    RESPONSES[Statement.new(text).type]
  end

end

class Statement
  attr_accessor :text

  def initialize(text)
    @text = text
  end

  def type
    if questioning?
      :question
    elsif shouting?
      :shout
    elsif silence?
      :silent
    else
      :unknown
    end
  end

  private

  def shouting?
    text.upcase == text && text.downcase != text
  end

  def questioning?
    text.end_with?('?') && !shouting?
  end

  def silence?
    text.strip == ''
  end
end
