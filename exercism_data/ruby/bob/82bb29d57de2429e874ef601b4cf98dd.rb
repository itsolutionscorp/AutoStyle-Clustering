class Bob
  def hey(greeting)
    responses = [
      {evaluation: ->(greeting) { greeting.strip.empty? }, response: 'Fine. Be that way!'},
      {evaluation: ->(greeting) { greeting.upcase =~ /[A-Z]/ && greeting == greeting.upcase }, response: 'Woah, chill out!'},
      {evaluation: ->(greeting) { greeting.chars.to_a.last == '?' }, response: 'Sure.'},
      {evaluation: ->(greeting) { true }, response: 'Whatever.'}
    ]

    responses.find { |response_maps| response_maps[:evaluation].call(greeting) }[:response]
  end
end
