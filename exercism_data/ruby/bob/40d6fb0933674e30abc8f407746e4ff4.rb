class Bob

  def hey(topic)
    phrase = Phrase.new(topic)

    if phrase.empty?
      'Fine. Be that way!'
    elsif phrase.exclamation?   
      'Woah, chill out!'
    elsif phrase.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Phrase
  attr_reader :topic

  def initialize(topic)
    @topic = topic.to_s
  end

  def empty?
    @topic.to_s.strip.empty?
  end

  def exclamation?
    @topic == @topic.upcase
  end

  def question?
    @topic.end_with?('?')
  end
end
