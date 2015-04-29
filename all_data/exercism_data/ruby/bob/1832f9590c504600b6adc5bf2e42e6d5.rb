class Bob
  def hey(message)
    return 'Fine. Be that way!' if nothing_to_say?  message
    return 'Woah, chill out!'   if shouting?        message
    return 'Sure.'              if question?        message
    return 'Whatever.'
  end

  def shouting?(message)
    contains_letters? message and all_uppercase? message
  end

  def question?(message)
    message.end_with? '?'
  end

  def nothing_to_say?(message)
    message.nil? or message.strip == ''
  end

  def contains_letters?(message)
    message.chars.select { |c| c =~ /[[:alpha:]]/ } != []
  end

  def all_uppercase?(message)
    message == message.upcase
  end
end
