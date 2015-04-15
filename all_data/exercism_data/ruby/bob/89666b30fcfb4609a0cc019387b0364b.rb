module SpeechRecognizer
  private
  def shouting
    lambda { |phrase| /[A-Za-z]/ =~ phrase &&
                      phrase == phrase.upcase }
  end

  def asking_a_question
    lambda { |phrase| phrase.end_with?('?') }
  end

  def silent
    lambda { |phrase| phrase.strip.empty? }
  end
end

class Bob
  include SpeechRecognizer

  def hey(phrase)
    case phrase
    when silent
      'Fine. Be that way!'
    when shouting
      'Woah, chill out!'
    when asking_a_question
      'Sure.'
    else
      'Whatever.'
    end
  end
end
