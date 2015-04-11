class Bob
  def hey(message)
    responses.find{|r| r.match?(message)}.response
  end

private
  def responses
    [ silent_response, shouting_response, question_response, default_response ]
  end

  def silent_response
    ResponseMatcher.new( ->(m){ m.nil? || m=='' }, "Fine. Be that way." )
  end
  def shouting_response
    ResponseMatcher.new( ->(m){ m.upcase == m }, "Woah, chill out!" )
  end
  def question_response
    ResponseMatcher.new( ->(m){ m =~ /\?$/ }, "Sure." )
  end
  def default_response
    ResponseMatcher.new( ->(m){ true }, "Whatever." )
  end

  class ResponseMatcher
    attr_accessor :matcher, :response
    def initialize(matcher, response)
      @matcher = matcher
      @response = response
    end
    def match?(input)
      matcher.call(input)
    end
  end
end
