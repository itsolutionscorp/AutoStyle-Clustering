class Bob

  def initialize
    @answers = {
      fine:     'Fine. Be that way.',
      asking:   'Sure.',
      shouting: 'Woah, chill out!',
      whatever: 'Whatever.'
    }
  end

  def hey(message)
    if fine?(message)
      @answers[:fine]
    elsif asking?(message)
      @answers[:asking]
    elsif shouting?(message)
      @answers[:shouting]
    else
      @answers[:whatever]
    end
  end

  def fine?(message)
    message.nil? || message.empty?
  end

  def asking?(message)
    message.end_with?('?')
  end

  def shouting?(message)
    message == message.upcase
  end

end
