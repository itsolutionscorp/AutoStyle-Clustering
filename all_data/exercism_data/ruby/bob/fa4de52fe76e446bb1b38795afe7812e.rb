class Bob
  def hey(saying)
    phrase = Phrase.new(saying)
    case
    when phrase.silence? then 'Fine. Be that way!'
    when phrase.loud? then 'Woah, chill out!'
    when phrase.question? then 'Sure.'
    else 'Whatever.'
    end
  end
end

class Phrase
  attr_reader :phrase
  def initialize(saying)
    @phrase = saying
  end

  def silence?
    phrase.strip.empty?
  end

  def loud?
    phrase =~ /[A-Z]/ && phrase.upcase == phrase
  end

  def question?
    phrase.end_with?('?')
  end
end
