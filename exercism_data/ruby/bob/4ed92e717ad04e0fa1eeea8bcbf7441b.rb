class Bob
  attr_reader :message

  def hey(message)
    @message = message
    return 'Fine. Be that way!' if silent?
    return 'Woah, chill out!'   if shouting?
    return 'Sure.'              if question?
    'Whatever.'
  end


  private

  def silent?
    message.strip.empty?
  end

  def question?
    message.end_with? '?'
  end

  def shouting?
     already_upcased? and contains_letters?
  end

  # String#upcase! returns nil if no changes made
  def already_upcased?
    message.upcase!.nil?
  end

  # ensures message contains uppercased letters
  def contains_letters?
    message.upcase =~ /[A-Z]/
  end
end
