module MessageHelper
  def silent? message
    message.strip.empty?
  end

  def loud? message
    message.upcase == message
  end

  def question? message
    message.end_with?("?")
  end
end

class Bob
  include MessageHelper

  def hey message
    if silent? message
      "Fine. Be that way!"
    elsif loud? message 
      "Woah, chill out!"  
    elsif question? message
      "Sure."
    else
      "Whatever."
    end
  end
end
