class Bob
  def hey(msg)
    case msg
    when empty?
      'Fine. Be that way!'
    when screaming?
      'Woah, chill out!'
    when asking?
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def empty?
    /\A(\s+)*\z/m
  end

  def screaming?
    /^[^a-z]+$/m
  end

  def asking?
    /\?\z/m
  end
end
