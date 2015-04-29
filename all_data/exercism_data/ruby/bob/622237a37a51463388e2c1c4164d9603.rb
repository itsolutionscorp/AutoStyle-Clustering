module Respondable
  def respond(message)
    possible_responses.detect { |response| response.reply_to(message) }.text
  end
end

class Bob
  include Respondable

  def hey(message)
    respond(message)
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
