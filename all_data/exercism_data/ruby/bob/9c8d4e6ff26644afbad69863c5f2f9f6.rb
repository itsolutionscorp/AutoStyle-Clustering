class Bob
  require 'ostruct'
  class Responder < OpenStruct
    def applys_for?(words)
      test.call(words)
    end
  end
  RESPONSE_FACTORY = Responder.method(:new)
  RESPONSES = [
    { response: "Fine. Be that way!", test: lambda { |words| !words || words.lstrip.empty? } },
    { response: "Woah, chill out!",   test: lambda { |words| words == words.upcase } },
    { response: "Sure.",              test: lambda { |words| words[-1] == "?" } },
    { response: "Whatever.",          test: lambda { |words| true } }
  ].map(&RESPONSE_FACTORY)

  def hey(words)
    appropriate_response = ->(potential_response) { potential_response.applys_for?(words) }
    RESPONSES.detect(&appropriate_response).response
  end
end
