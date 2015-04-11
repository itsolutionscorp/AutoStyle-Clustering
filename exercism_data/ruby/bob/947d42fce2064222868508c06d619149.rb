class BobsResponse
  attr_reader :answer

  def initialize(regex, answer)
    @regex = regex
    @answer = answer
  end

  def for_question?(string)
    string.to_s.strip =~ @regex
  end
end

class ResponseToEmpty < BobsResponse
  def initialize(answer)
    super /\A\z/, answer
  end
end

class ResponseToYell < BobsResponse
  def initialize(answer)
    super /\A[^a-z]*\z/, answer
  end
end

class ResponseToQuestion < BobsResponse
  def initialize(answer)
    super /\?\z/, answer
  end
end

class ResponseToAnything < BobsResponse
  def initialize(answer)
    @answer = answer
  end

  def for_question?(string); true; end
end

class Bob

  BOBS_RESPONSES = [
    ResponseToEmpty.new('Fine. Be that way!'),
    ResponseToYell.new('Woah, chill out!'),
    ResponseToQuestion.new('Sure.'),
    ResponseToAnything.new("Whatever.")
  ]

  def hey(question)
    BOBS_RESPONSES.detect do |response|
      response.for_question?(question)
    end.answer
  end
end
