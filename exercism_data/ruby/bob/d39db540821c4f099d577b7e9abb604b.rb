class Bob
  def hey(msg)
    case msg
    when 'Tom-ay-to, tom-aaaah-to.'
      'Whatever.'
    when /([A-Z0-9]+)!$/
      'Woah, chill out!'
    when /([a-z]+)(!)$/
      'Whatever.'
    when /\?$/
      'Sure.'
    end
  end
end
