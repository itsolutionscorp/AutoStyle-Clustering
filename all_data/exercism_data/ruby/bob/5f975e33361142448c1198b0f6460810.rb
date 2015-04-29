class Bob

  def hey message
    message.extend(Language)

    case
      when message.is_blank?     then "Fine. Be that way!"
      when message.is_aggresive? then "Woah, chill out!"
      when message.is_question?  then "Sure."
    else
      "Whatever."
    end
  end

end

module Language

  def is_aggresive?
    upcase == self
  end

  def is_question?
    end_with? '?'
  end

  def is_blank?
    nil == self || "" == self
  end

end
