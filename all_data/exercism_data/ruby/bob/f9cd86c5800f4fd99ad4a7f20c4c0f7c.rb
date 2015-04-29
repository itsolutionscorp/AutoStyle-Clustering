class Bob
  ANSWERS = {
    naught:   'Fine. Be that way!',
    yell:     'Woah, chill out!',
    question: 'Sure.',
    default:  'Whatever.'
  }

  def hey(speak)
    if naught? speak
      ANSWERS[:naught]
    elsif yell? speak
      ANSWERS[:yell]
    elsif question? speak
      ANSWERS[:question]
    else
      ANSWERS[:default]
    end
  end

  private

  def question?(speak)
    speak.end_with? '?'
  end

  def yell?(speak)
    speak.upcase == speak
  end

  def naught?(speak)
    speak.strip.empty?
  end
end
