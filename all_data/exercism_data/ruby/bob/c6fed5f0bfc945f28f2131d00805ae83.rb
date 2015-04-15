class Bob
  def hey(phrase)
    case interpret(phrase)
      when :silence  then 'Fine. Be that way!'
      when :shouting then 'Woah, chill out!'
      when :question then 'Sure.'
      else 'Whatever.'
    end
  end

  private
  def interpret(phrase)
    return :silence  if phrase.strip.empty?
    return :shouting if phrase.upcase == phrase
    return :question if phrase.strip.end_with?('?')
    :normal
  end
end
