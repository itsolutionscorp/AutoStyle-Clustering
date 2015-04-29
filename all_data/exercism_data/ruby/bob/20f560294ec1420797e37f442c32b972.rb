class Bob
  def hey(message)
    empty_message = EmptyMessage.new
    question_message = QuestionMessage.new
    yelling_message = YellingMessage.new
    no_match_message = NoMatchMessage.new

    responses = [empty_message, question_message, yelling_message, no_match_message]
    found_response = responses.find{ |response| response.match?(message) }
    found_response.respond
  end
end

class EmptyMessage
  def match?(message)
    message == "" || message.nil? || message.gsub(/\s+/, "") == ""
  end

  def respond
    "Fine. Be that way!"
  end
end

class QuestionMessage
  def match?(message)
    message[message.length-1] == "?" && message.upcase != message
  end

  def respond
    "Sure."
  end
end

class YellingMessage
  def match?(message)
    message == message.upcase
  end

  def respond
    "Woah, chill out!"
  end
end

class NoMatchMessage
  def match?(message)
    true
  end

  def respond
    "Whatever."
  end
end
