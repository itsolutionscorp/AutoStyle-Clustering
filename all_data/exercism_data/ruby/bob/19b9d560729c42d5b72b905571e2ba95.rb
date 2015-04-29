class Bob
  TEENAGE_RESPONSES = Hash.new('Whatever.').merge({
    :silence => 'Fine. Be that way!',
    :shouting => 'Woah, chill out!',
    :question => 'Sure.',
  })

  def hey(message, responses = TEENAGE_RESPONSES)
    type = message_type(message)
    responses[type]
  end

  private

  def message_type(message)
    if message.strip.empty?
      :silence
    elsif message.upcase == message and /[a-z]/i =~ message
      :shouting
    elsif message.end_with? '?'
      :question
    else
      :other
    end
  end
end
