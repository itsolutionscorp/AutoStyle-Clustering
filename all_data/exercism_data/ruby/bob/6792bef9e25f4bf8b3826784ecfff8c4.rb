class Bob

  def hey(message_string)
    message = Message.new(message_string)
    TeenagerResponse.new(message).response
  end

end

class Message

  def initialize(message)
    @message = message || ""
  end

  def question?
    @message.end_with? "?"
  end  

  def yelling?
    @message.upcase == @message
  end

  def silent?
    @message.strip == ""
  end

end

class TeenagerResponse

  def initialize(message)
    @message = message
    @responses = [SilenceResponse, YellingResponse, QuestionResponse, DefaultResponse]
    @responses = @responses.map{|r| r.new(message)}
  end

  def response
    appropriate_responses = @responses.select {|r| r.appropriate?}
    appropriate_responses.first.response
  end

end

class QuestionResponse

  def initialize(message)
    @message = message
  end

  def appropriate?
    @message.question?
  end

  def response
    "Sure."
  end

end

class YellingResponse

  def initialize(message)
    @message = message
  end

  def appropriate?
    @message.yelling?
  end

  def response
    "Woah, chill out!"
  end

end

class SilenceResponse

  def initialize(message)
    @message = message
  end

  def appropriate?
    @message.silent?
  end

  def response
    "Fine. Be that way!"
  end

end

class DefaultResponse

  def initialize(message)
    @message = message
  end

  def appropriate?
    true
  end

  def response
    "Whatever."
  end

end
