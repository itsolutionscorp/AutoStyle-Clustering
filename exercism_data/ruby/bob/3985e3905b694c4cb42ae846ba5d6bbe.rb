class Bob

  def hey message
    if silent? message
      "Fine. Be that way."
    elsif spazzing? message
      "Woah, chill out!"
    elsif question? message
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def spazzing? message
     message =~ /^[%^*@#\$)(,0-9A-Z!\s]*$/
  end

  def question? message
    message =~ /\?$/
  end

  def silent? message
    message == ""
  end

end
