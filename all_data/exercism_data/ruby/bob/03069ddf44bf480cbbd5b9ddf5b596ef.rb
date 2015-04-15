class Bob
  RESPONSES = [
    { response: "Fine. Be that way!", test: lambda { |words| !words || words.lstrip.empty? } },
    { response: "Woah, chill out!",   test: lambda { |words| words == words.upcase } },
    { response: "Sure.",              test: lambda { |words| words.end_with? "?" } },
    { response: "Whatever.",          test: lambda { |words| true } }
  ]

  def hey(words)
    appropriate_response = ->(potential_response) { potential_response[:test].call(words) }
    RESPONSES.detect(&appropriate_response)[:response]
  end
end
