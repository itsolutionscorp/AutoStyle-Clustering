class Bob
  def hey(message)
    case String(message)
    when silent?
      'Fine. Be that way!'
    when shouting?
      'Woah, chill out!'
    when nosy?
      'Sure.'
    else
      'Whatever.'
    end
  end

  private
  def shouting?
    @shouting ||= ->(text) { text.upcase == text }
  end

  def silent?
    @silent ||= ->(text) { text.strip.empty? }
  end

  def nosy?
    @nosy ||= ->(text) { text.end_with?('?') }
  end
end
