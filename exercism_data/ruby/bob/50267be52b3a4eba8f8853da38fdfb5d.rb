class Bob
  def hey(message)
    Conversation.new(message, self).respond
  end

  def possible_responses
    [
      blank_response,
      shouting_response,
      question_response,
      default_response
    ]
  end

  private

  def blank_response
    Response.new(text: "Fine. Be that way!", if: Proc.new { |message| message.nil? || message.empty? })
  end

  def shouting_response
    Response.new(text: "Woah, chill out!", if: Proc.new { |message| message.upcase == message })
  end

  def question_response
    Response.new(text: "Sure.", if: Proc.new { |message| message.end_with?('?') })
  end

  def default_response
    Response.new(text: "Whatever.")
  end
end

class Conversation
  attr_reader :message, :person

  def initialize(message, person)
    @message = message
    @person = person
  end

  def respond
    person.possible_responses.detect { |response| response.reply_to(message) }.text
  end
end

class Response
  attr_reader :text, :proc

  def initialize(options = {})
    @text = options[:text]
    @proc = options[:if] || Proc.new { true }
  end

  def reply_to(message)
    text if proc.call(message)
  end
end
