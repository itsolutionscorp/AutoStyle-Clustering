class Incoming < String

  def question?
    end_with? '?'
  end

  def yelled?
    upcase == self
  end

  def nothing?
    empty?
  end

end

class Bob

  def hey(incoming)
    incoming = Incoming.new incoming.to_s
    case
    when incoming.nothing?
      'Fine. Be that way.'
    when incoming.yelled?
      'Woah, chill out!'
    when incoming.question?
      'Sure.'
    else
      'Whatever.'
    end
  end

end
