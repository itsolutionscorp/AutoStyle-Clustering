class Responder 
  def respond(statement, result)
    result ||= @response if matcher.match(statement)
    result
  end

  def response
    @respone ||= "Fine. Be that way."
  end

  def matcher
    @matcher ||= /\A\z/
  end

  def to(matcher)
    @matcher = matcher
    self
  end

  def with(response)
    @response = response
    self
  end
end

class Bob
  CHAT = /\A.*\z/
  SILENCE = /\A\z/
  QUESTION = /\?\z/
  SHOUT = /\A[^a-z]+\z/
  
  def initialize
    @responders = [ respond.to(SILENCE).with("Fine. Be that way."),
                    respond.to(QUESTION).with("Sure."),
                    respond.to(SHOUT).with("Woah, chill out!"),
                    respond.to(CHAT).with("Whatever.") ]
  end

  def hey(statement)
    @responders.inject(false) { |result,m| m.respond(statement, result) } || "Fine. Be that way."
  end
 
  def respond
    Responder.new
  end
end
