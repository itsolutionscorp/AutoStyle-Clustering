class Bob
  def hey(message)
    responses.find{|r| r.match?(message)}.response
  end

private
  def responses
    [ silent_response, shouting_response, question_response, default_response ]
  end

  def silent_response
    ResponseMatcher.new "Fine. Be that way." do |m|
      m.nil? || m==''
    end
  end

  def shouting_response
    ResponseMatcher.new "Woah, chill out!" do |m|
      m.upcase == m
    end
  end

  def question_response
    ResponseMatcher.new "Sure." do |m|
      m =~ /\?$/
    end
  end

  def default_response
    ResponseMatcher.new "Whatever." do |m|
      true
    end
  end

  class ResponseMatcher
    attr_accessor :matcher, :response
    def initialize(response, &matcher)
      @matcher = matcher
      @response = response
    end
    def match?(input)
      matcher.call(input)
    end
  end
end
