class Bob
  def hey(message)
    case
      when silent(message)
        'Fine. Be that way!'
      when yelling(message)
        'Woah, chill out!'
      when question(message)
        'Sure.'
      else
        'Whatever.'
    end
  end

  private

  def silent(message)
    message.to_s.empty?
  end

  def yelling(message)
    message == message.upcase
  end

  def question(message)
    message.chars.to_a.last == '?'
  end
end
