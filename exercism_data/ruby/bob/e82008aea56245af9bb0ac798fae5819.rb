require 'active_support/core_ext/object/blank'

class Bob
  def says_nothing? message
    message.blank?
  end
  
  def question? message
    message.end_with? '?'
  end
  
  def yelling? message
    message == message.upcase
  end
  
  def hey message
    case
    when says_nothing?(message)
      "Fine. Be that way."
    when question?(message)
      "Sure."
    when yelling?(message)
      "Woah, chill out!"
    else
      "Whatever."  
    end
  end
end
