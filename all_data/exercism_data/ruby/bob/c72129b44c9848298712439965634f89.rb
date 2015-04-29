class Bob
  def hey(sentence)
    case sentence
    when /([a-z](\.|\!)$)/
      'Whatever.'
    when /(\?$)/
      'Sure.'
    when /(\!$)/, /[A-Z]/
      'Woah, chill out!'
    else
      'Fine. Be that way.'
    end
  end
end
