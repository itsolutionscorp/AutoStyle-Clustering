require 'active_support/core_ext'

class Bob
  def hey(message)
    if message.blank?
      "Fine. Be that way!"
    elsif message.upcase == message
      "Woah, chill out!"
    elsif message.end_with?('?')
      "Sure."
    else
      "Whatever."
    end
  end
end
