class Bob

  def hey(str)
    string = StringInterpreter.new(str)
    handle_responses
  end


  private

  def handle_reponses
    %w(blank exclamation question).each do |type|
      if string.send("#{type}?")
        return responder.send("#{type}_response")
      end
    end
    return responder.default_response
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
