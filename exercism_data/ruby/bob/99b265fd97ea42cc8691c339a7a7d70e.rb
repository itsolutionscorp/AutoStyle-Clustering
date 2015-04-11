class Bob
  def initialize
    @responders = [
      Responder.new('shouting', /([A-Z]!?$|[A-Z]+\s+[A-Z]+)/, 'Whoa, chill out!'),
      Responder.new('question', /[a-z]*\?\Z/, 'Sure.'),
      Responder.new('silence', /(\A\s+$\Z|\A\Z)/, 'Fine. Be that way!'),
      Responder.new('default', //, 'Whatever.'),
    ]
  end

  def hey(remark)
    @responders.find{|r| r.has_response_for(remark) }.response
  end
end

class Responder
  attr_reader :name, :parser, :response

  def initialize(name, parser, response)
    @parser = parser
    @response = response
  end

  def has_response_for(remark)
    parser =~ remark
  end
end
