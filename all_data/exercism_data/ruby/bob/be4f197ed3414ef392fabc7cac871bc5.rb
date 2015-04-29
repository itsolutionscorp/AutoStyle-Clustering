class Bob
  def hey(input)
    case
    when empty?(input)
      'Fine. Be that way.'
    when question?(input)
      'Sure.'
    when shout?(input)
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end

  private
  def question?(input)
    input.end_with? '?'
  end

  def shout?(input)
    input.upcase.eql? input
  end

  def empty?(input)
    input.to_s.eql?("")
  end
end
