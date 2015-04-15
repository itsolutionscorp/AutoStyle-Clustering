class Bob
  def hey(words)
    speech = Speech.new(words)

    if speech.silent?
      'Fine. Be that way!'
    elsif speech.shouting?
      'Woah, chill out!'
    elsif speech.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Speech < String
  def silent?
    strip.empty?
  end

  def shouting?
    match(/[A-z]+/) && upcase == self
  end

  def question?
    end_with?('?')
  end
end
