class Bob

  def hey(str)
    @string = StringInterpreter.new(str)
    handle_responses
  end

  private

  def handle_responses
    %w(blank exclamation question).each do |type|
      return responder.send("#{type}_response") if @string.send("#{type}?")
    end
    responder.default_response
  end

  def responder
    Responder.new
  end

end

class StringInterpreter

  attr_reader :string

  def initialize(string)
    @string = string
  end

  def blank?
    string.nil? || string.empty?
  end

  def exclamation?
    string.upcase == string
  end

  def question?
    string =~ /\?$/
  end

end

class Responder

  def blank_response
    'Fine. Be that way.'
  end

  def exclamation_response
    'Woah, chill out!'
  end

  def question_response
    'Sure.'
  end

  def default_response
    'Whatever.'
  end

end
