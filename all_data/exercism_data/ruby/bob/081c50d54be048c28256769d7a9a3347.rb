class Bob
  def hey(message)
    @message = message
    return 'Fine. Be that way!' if  silence?
    return 'Woah, chill out!' if yelling?
    return 'Sure.' if question?
    'Whatever.'
  end

  private

  def silence?
    @message.to_s.strip.empty?
  end

  def yelling?
    @message == @message.upcase
  end

  def question?
    @message.end_with?('?')
  end
end
