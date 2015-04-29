class Bob

  def hey(conversation)
    if silent?(conversation)
      'Fine. Be that way!'
    elsif shouting?(conversation)
      'Woah, chill out!'
    elsif question?(conversation)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def silent?(conversation)
    conversation.strip.empty?
  end

  def question?(conversation)
    conversation[-1] == '?'
  end

  def shouting?(conversation)
    conversation !~ /[a-z]/ && conversation =~ /[A-Z]/
  end

end
