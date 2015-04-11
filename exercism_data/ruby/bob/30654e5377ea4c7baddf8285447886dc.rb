class Bob
  def hey(speak)
    if naught? speak
      'Fine. Be that way!'
    elsif yell? speak
      'Woah, chill out!'
    elsif question? speak
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def question?(speak)
    speak[-1] == '?'
  end

  def yell?(speak)
    speak.upcase == speak
  end

  def naught?(speak)
    speak.empty? || speak.match(/^\s*$/)
  end
end
