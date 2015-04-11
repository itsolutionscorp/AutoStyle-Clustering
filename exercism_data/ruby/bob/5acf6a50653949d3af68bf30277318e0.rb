class Interactor
  DEFAULT_RESPONSE = 'Whatever.'

  def initialize(next_link = nil)
    @next_link = next_link
  end

  def respond_to(interaction)
    return response if matches?(interaction)
    @next_link ? @next_link.respond_to(interaction) : DEFAULT_RESPONSE
  end

  private

  def empty?(interaction)
    interaction.nil? || interaction.strip.empty?
  end

  def matches?(interaction)
    raise NameError.new("should be implemented in concrete implementation")
  end

  def response
    raise NameError.new("should be implemented in concrete implementation")
  end
end

class SilentInteractor < Interactor
  def matches?(interaction)
    empty?(interaction)
  end

  def response
    'Fine. Be that way!'
  end
end

class ShoutingInteractor < Interactor
  def matches?(interaction)
    empty?(interaction) ? false : interaction.upcase == interaction
  end

  def response
    'Woah, chill out!'
  end
end

class QuestionInteractor < Interactor
  def matches?(interaction)
    empty?(interaction) ? false : interaction.end_with?('?')
  end

  def response
    'Sure.'
  end
end

class Bob
  def hey(interaction, interactor = default_behaviour)
    interactor.respond_to(interaction)
  end

  private

  def default_behaviour
    SilentInteractor.new(ShoutingInteractor.new(QuestionInteractor.new))
  end
end
