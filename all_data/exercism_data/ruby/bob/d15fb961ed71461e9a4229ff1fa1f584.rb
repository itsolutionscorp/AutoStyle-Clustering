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
      @answers.fetch(:fine)
    elsif asking?(message)
      @answers.fetch(:asking)
    elsif shouting?(message)
      @answers.fetch(:shouting)
    else
      @answers.fetch(:whatever)
    end
  end

  def fine?(message)
    message.empty?
  end

  def asking?(message)
    message.end_with?('?')
  end

  def shouting?(message)
    message == message.upcase
  end

end
