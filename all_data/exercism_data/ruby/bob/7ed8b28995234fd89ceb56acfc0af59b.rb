class Bob
  def hey(greeting)
    return 'Woah, chill out!'   if shouting?(greeting)
    return 'Sure.'              if question?(greeting)
    return 'Fine. Be that way!' if silent?(greeting)
    'Whatever.'
  end

  def has_words?(greeting)
    greeting.match(/[[:alpha:]]/)
  end
  
  def shouting?(greeting)
    has_words?(greeting) && greeting.upcase == greeting
  end

  def question?(greeting)
    greeting.end_with?('?')
  end

  def silent?(greeting)
    greeting.strip == ''
  end
end
