class Bob
  def hey(speak)
    case
    when silent?(speak)
      "Fine. Be that way!"
    when shouting?(speak)
      "Woah, chill out!"
    when questioning?(speak)
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def silent?(speak)
    speak.strip.empty? || speak.nil?
  end

  def shouting?(speak)
    speak.upcase == speak
  end

  def questioning?(speak)
    speak.end_with?("?")
  end
end
