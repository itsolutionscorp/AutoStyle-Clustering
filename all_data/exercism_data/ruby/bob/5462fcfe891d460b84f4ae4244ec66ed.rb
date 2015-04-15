class Bob

  def hey(greeting)
    case greeting
      when shouting(greeting) then 'Woah, chill out!'
      when asking_a_question then 'Sure.'
      when silence then 'Fine. Be that way!'
      else 'Whatever.'
    end
  end

private

  def shouting(greeting)
    return false if greeting.match(/[a-z]/)
    /[A-Z]{2,}/
  end

  def asking_a_question
    /\?\z/
  end

  def silence
    /^\s*\z/
  end

end
