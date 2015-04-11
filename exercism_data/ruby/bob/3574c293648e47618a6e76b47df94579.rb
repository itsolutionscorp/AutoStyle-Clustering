class Bob

  def hey(msg)
    case msg
    when empty    then ignore
    when angry    then placate
    when question then affirm
    else               confuse
    end
  end

  private

  def empty
    /\A\s*\Z/
  end

  def angry
    /\A([^a-z]+|[A-Z]+)[A-Z]+[^a-z]+\Z/
  end

  def question
    /\A.*\?\Z/
  end

  def ignore
    'Fine. Be that way!' 
  end

  def placate
    'Woah, chill out!'
  end

  def affirm
    'Sure.'
  end

  def confuse
    'Whatever.'
  end
end
