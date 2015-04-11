class Bob

  def hey(msg)
    return 'Fine. Be that way!' if empty_or_nil?(msg)
    return 'Woah, chill out!'   if forceful?(msg)
    return 'Sure.'              if question?(msg)

    'Whatever.'
  end

  private
  def empty_or_nil?(msg)
    msg.nil? || contains_no_word_characters(msg)
  end

  def forceful?(msg)
    msg == msg.upcase
  end

  def question?(msg)
    msg.end_with?('?')
  end

  def contains_no_word_characters(msg)
    msg =~ /^[^\w]*$/
  end

end
