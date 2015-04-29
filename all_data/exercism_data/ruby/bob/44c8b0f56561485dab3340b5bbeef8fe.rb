class Bob
  def hey(message)
    case message.to_s
    when its(:silent?)
      'Fine. Be that way!'
    when its(:shouting?)
      'Woah, chill out!'
    when its(:nosy?)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private
  def its(name)
    responses[name]
  end

  def responses
    @responses ||= {
      silent?:   ->(text) { text.strip.empty? },
      shouting?: ->(text) { text.upcase == text },
      nosy?:     ->(text) { text.end_with?('?') },
    }
  end
end
