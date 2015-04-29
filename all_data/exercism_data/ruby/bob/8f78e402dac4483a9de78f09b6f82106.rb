# encoding: UTF-8
class Bob
  def hey(message)
    last_char = message.nil? ? nil : message[-1]
    case last_char
    when "."
      "Whatever."
    when "?"
      "Sure."
    when nil
      "Fine. Be that way."
    else
      if message.upcase == message
        "Woah, chill out!"
      else
        "Whatever."
      end
    end
  end
end
