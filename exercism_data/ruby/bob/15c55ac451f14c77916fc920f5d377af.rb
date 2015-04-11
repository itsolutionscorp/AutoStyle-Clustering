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

class Bob

  BOBS_RESPONSES = [
    BobsResponse.new(/^$/        , 'Fine. Be that way!'),
    BobsResponse.new(/^[^a-z]*$/ , 'Woah, chill out!'),
    BobsResponse.new(/\?$/       , 'Sure.'),
    BobsResponse.new(//          , "Whatever.")
  ]

  def hey(question)
    BOBS_RESPONSES.detect do |response|
      response.for_question?(question)
    end.answer
  end
end
