class Bob
  def hey(input)
    case
    when is_empty?(input)
      'Fine. Be that way.'
    when is_question?(input)
      'Sure.'
    when is_shout?(input)
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end

  private
  def is_question?(input)
    input.end_with? '?'
  end

  def is_shout?(input)
    input.upcase.eql? input
  end

  def is_empty?(input)
    input.nil? || input.eql?("")
  end
end
