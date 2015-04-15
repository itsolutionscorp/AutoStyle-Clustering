require 'active_support'

class Bob
  def hey message
    return "Fine. Be that way!" if empty?(message)
    return "Woah, chill out!" if yell?(message)
    return "Sure." if question?(message)

    "Whatever."
  end

  private

  def empty? message
    message.gsub(/\s/, '').empty?
  end

  def yell? message
    meaning = message.gsub(/[^[:alpha:]]/, '')
    !empty?(meaning) and meaning.upcase == meaning
  end

  def question? message
    message.end_with? "?"
  end

end
