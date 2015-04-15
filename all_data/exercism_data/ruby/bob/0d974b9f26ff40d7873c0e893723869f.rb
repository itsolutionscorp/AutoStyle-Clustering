module Respondable
  def respond_to(message)
    possible_responses.detect { |response| response.respond_to(message) }
  end
end

class Bob
  include Respondable

  def hey(message)
    respond_to(message).text
  end

  def possible_responses
    [
      { with: "Fine. Be that way!", if: Proc.new { |message| message.nil? || message.empty? } },
      { with: "Woah, chill out!", if: Proc.new { |message| message.upcase.eql?(message) } },
      { with: "Sure.", if: Proc.new { |message| text.end_with?('?') } },
      { with: "Whatever.", if: Proc.new { true } }
    ].map { |options| Response.new(options) }
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
