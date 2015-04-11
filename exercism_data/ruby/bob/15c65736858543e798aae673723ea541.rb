class Bob

  def hey(topic)
    phrase = Phrase.new(topic)

  case 
    when phrase.is_empty?
      'Fine. Be that way!'
    when phrase.is_exclamation?   
      'Woah, chill out!'
    when phrase.is_question?
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

  def is_empty?
    @topic.to_s.strip.empty?
  end

  def is_exclamation?
    @topic == @topic.upcase
  end

  def is_question?
    @topic.end_with?('?')
  end
 end
