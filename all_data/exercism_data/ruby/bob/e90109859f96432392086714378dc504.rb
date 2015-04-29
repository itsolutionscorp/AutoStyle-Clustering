class Bob
  # list of matcher functions with reponses.
  # the order matters
  @matchers = {
    matches_question: 'Sure.',
    matches_yelling: 'Woah, chill out!',
    matches_whatever: 'Whatever.',
  }
  
  def initialize
    @default_message = 'Fine. Be that way.'

  end

  def hey(message)
    case
    when matches_question?(message)
      'Sure.'
    when matches_yelling?(message)
      'Woah, chill out!'
    when matches_whatever(message)
      'Whatever.'
    else
      'Fine. Be that way'
    end
  end

  private

  def matches_question?(message)
    message.end_with?('?')
  end

  def matches_whatever?(message)
    message.end_with?('.') or message.end_with?('!')
  end

  def matches_yelling?(message)
    message.upcase == message
  end
end
