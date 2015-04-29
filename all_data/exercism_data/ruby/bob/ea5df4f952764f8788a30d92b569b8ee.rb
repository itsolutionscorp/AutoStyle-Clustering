class Response
  attr_reader :say

  def initialize(say, check)
    @check = check
    @say = say
  end

  def for?(greeting)
    return @check.call(greeting) if @check.respond_to? :call
    greeting.match @check
  end
end

class BobResponses
  attr_reader :responses

  def initialize
    @responses = [
      shouting, question, silence, default
    ]
  end

  def response_for(greeting)
    responses.find { |response| response.for? greeting }
  end

private

  def shouting
    Response.new(
      "Woah, chill out!",
      Proc.new do |greeting|
        !greeting.match(/[a-z]/) && greeting.match(/[A-Z]{2,}/)
      end
    )
  end

  def question
    Response.new("Sure.", /\?\z/)
  end

  def silence
    Response.new("Fine. Be that way!", /^\s*\z/)
  end

  def default
    Response.new("Whatever.", /.*/)
  end

end


class Bob
  attr_reader :bob_responses

  def initialize
    @bob_responses = BobResponses.new
  end

  def hey(greeting)
    bob_responses.response_for(greeting).say
  end

end
