class Bob
  def initialize
    @message
  end

  def hey(message)
    @message = message
    return 'Fine. Be that way!' if  @message.to_s.strip.empty?
    return 'Woah, chill out!' if yelling?
    return 'Sure.' if question?
    'Whatever.'
  end

  def yelling?
    @message == @message.upcase
  end

  def question?
    @message.end_with?('?')
  end

  private :yelling?, :question?
end
