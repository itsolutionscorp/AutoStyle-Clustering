class Bob

  def hey(prompt)
    case
    when shouting?(prompt) then 'Whoa, chill out!'
    when question?(prompt) then 'Sure.'
    when blank?(prompt)    then 'Fine. Be that way!'
    else 'Whatever.'
    end
  end

  def shouting?(prompt)
    /[A-Z]/.match(prompt) && !(/a-z/.match(prompt))
  end

  def question?(prompt)
    /\?\z/.match(prompt) && !shouting?(prompt)
  end

  def blank?(prompt)
    prompt.strip.empty?
  end

end
