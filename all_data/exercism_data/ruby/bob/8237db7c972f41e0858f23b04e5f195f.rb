require 'active_support/core_ext'

class Bob
  def hey(message)
    if not_saying_anything?(message)
      "Fine. Be that way!"
    elsif yelling?(message)
      "Woah, chill out!"
    elsif question?(message)
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def not_saying_anything?(message)
    message.blank?
  end

  def yelling?(message)
    message.upcase == message
  end

  def question?(message)
    message.end_with?('?')
  end
end
