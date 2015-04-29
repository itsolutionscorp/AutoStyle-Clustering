module Respondable
  def respond_to(message)
    possible_responses.detect { |response| response.respond_to(message) }
  end
end

class Bob
  include Respondable

  def hey(message)
    respond_to(Message.new(message)).text
  end

  def possible_responses
    [
      { with: "Fine. Be that way!", if: Proc.new { |message| message.silence? } },
      { with:"Woah, chill out!", if: Proc.new { |message| message.shouting? } },
      { with:"Sure.", if: Proc.new { |message| message.question? } },
      { with:"Whatever.", if: Proc.new { true } }
    ].map { |options| Response.new(options) }
  end
end

class Message
  attr_reader :text

  def initialize(text)
    @text = text
  end

  def silence?
    text.nil? || text.empty?
  end

  def shouting?
    text.upcase.eql?(text)
  end

  def question?
    text.end_with?('?')
  end
end

class Response
  attr_reader :text, :proc

  def initialize(options = {})
    @text = options[:with]
    @proc = options[:if]
  end

  def respond_to(message)
    text if proc.call(message)
  end
end
