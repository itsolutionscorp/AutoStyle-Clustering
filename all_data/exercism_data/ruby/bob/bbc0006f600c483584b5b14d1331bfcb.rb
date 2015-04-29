# Bob

class Bob
  def hey(phrase)
    phrase_interpretation = interpret(phrase)
    response_to(phrase_interpretation)
  end

private

  def response_to(phrase_interpretation)
    case phrase_interpretation
    when :shouting
      'Woah, chill out!'
    when :question
      'Sure.'
    when :silence
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  def interpret(phrase)
    return :shouting if all_caps?(phrase)
    return :question if phrase.end_with?('?')
    return :silence  if phrase.strip.empty?
  end

  def all_caps?(phrase)
    return false unless phrase.match(/[a-z]/i)
    phrase == phrase.upcase
  end
end
