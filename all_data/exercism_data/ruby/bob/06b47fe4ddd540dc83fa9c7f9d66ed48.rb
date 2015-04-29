class Bob
  def hey(input)
    @input = input

    return "Fine. Be that way!" if silence?
    return "Woah, chill out!" if forceful_question?
    return "Sure." if asking_a_question?
    return "Woah, chill out!" if shouting?

    'Whatever.' if stating_something?
  end

  def stating_something?
    @input.length > 0
  end

  def shouting?
    @input == @input.upcase
  end

  def asking_a_question?
    @input[-1] == '?'
  end

  def talking_forcefully?
    @input[-1] == '!'
  end

  def forceful_question?
    asking_a_question? && shouting?
  end

  def silence?
     @input.nil? || @input.strip == ''
  end

end
