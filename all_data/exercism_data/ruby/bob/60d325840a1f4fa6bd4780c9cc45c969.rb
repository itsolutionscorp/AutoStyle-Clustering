module SpeechRecognizer
  private
  def shouting?(phrase)
    /[A-Za-z]/ =~ phrase &&
    phrase == phrase.upcase
  end

  def asking_a_question?(phrase)
    phrase.end_with?('?')
  end

  def silent?(phrase)
    phrase.strip.empty?
  end
end

class Bob
  include SpeechRecognizer

  def hey(phrase)
    case 
    when silent?(phrase)
      'Fine. Be that way!'
    when shouting?(phrase)
      'Woah, chill out!'
    when asking_a_question?(phrase)
      'Sure.'
    else
      'Whatever.'
    end
  end
end
