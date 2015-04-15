class Bob
  def hey message
    @message = message

    return 'Fine. Be that way!' if silence?
    return 'Woah, chill out!' if shouting?
    return 'Sure.' if question?
    'Whatever.'
  end

  def silence?
    @message.strip.empty?
  end

  def shouting?
    has_at_least_one_letter? && has_no_lowercase_letters?
  end

  def question?
    @message.end_with? '?'
  end

  def has_at_least_one_letter?
    @message.chars.any? {|character| character.match /[[:alpha:]]/}
  end

  def has_no_lowercase_letters?
    @message.chars.none? {|character| character.match /[[:lower:]]/}
  end
end
