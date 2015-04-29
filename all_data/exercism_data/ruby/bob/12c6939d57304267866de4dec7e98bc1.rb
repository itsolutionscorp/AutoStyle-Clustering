class Bob
  def hey(message)
    return 'Fine. Be that way!' if silent? message
    return 'Woah, chill out!'   if shouting? message
    return 'Sure.'              if question? message
    'Whatever.'
  end

  private

  def silent?(message)
    message.strip.empty?
  end

  def question?(message)
    message.end_with? '?'
  end

  def shouting?(message)
     already_upcased?(message) && contains_letters?(message)
  end

  def already_upcased?(str)
    str.upcase!.nil?      # String#upcase! returns nil if no changes made
  end

  def contains_letters?(str)
    str.upcase =~ /[A-Z]/  # ensures message contains letters
  end
end
