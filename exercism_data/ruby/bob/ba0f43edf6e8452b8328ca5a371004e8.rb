class Bob
  def initialize
    @default_message = 'Fine. Be that way.'

    # list of matcher functions with reponses.
    # the order matters
    @matchers = {
      matches_question: 'Sure.',
      matches_yelling: 'Woah, chill out!',
      matches_whatever: 'Whatever.',
    }
  end

  def hey(message)
    if message and message.size > 0
      @matchers.each do |matcher, response|
        return response if send(matcher, message)
      end
    end

    @default_message
  end

  private

  def matches_question(message)
    message.end_with?('?')
  end

  def matches_whatever(message)
    message.end_with?('.') or message.end_with?('!')
  end

  def matches_yelling(message)
    message.upcase == message
  end
end
