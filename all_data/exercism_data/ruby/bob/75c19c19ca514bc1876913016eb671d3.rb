class Bob
  SENTANCE_ALL_CAPS = /\A[A-Z\W]+\Z/
  VERY_EXCITED = /!{2,10}/
  SENTANCE_ALL_CAPS_NUMBERS = /([0-9]+[, ]+)+[A-Z!]+/
  ENDS_WITH_QUESTION = /\?\Z/

  def hey(statement)
    response = "Whatever."
    case statement.strip
    when SENTANCE_ALL_CAPS, VERY_EXCITED, SENTANCE_ALL_CAPS_NUMBERS
      response = "Woah, chill out!"
    when ENDS_WITH_QUESTION
      response = "Sure."
    when ""
      response = "Fine. Be that way!"
    end
    response
  end
end
