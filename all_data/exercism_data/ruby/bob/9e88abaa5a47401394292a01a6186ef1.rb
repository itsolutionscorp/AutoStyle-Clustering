class Bob
  def hey(greeting)
    responses = [
      {evaluation: ->(greeting) { _empty?(greeting) }, response: 'Fine. Be that way!'},
      {evaluation: ->(greeting) { _shouting?(greeting) }, response: 'Woah, chill out!'},
      {evaluation: ->(greeting) { _question?(greeting) }, response: 'Sure.'},
      {evaluation: ->(greeting) { true }, response: 'Whatever.'}
    ]

    responses.find { |response_maps| response_maps[:evaluation].call(greeting) }[:response]
  end

  def _question?(greeting)
    greeting.end_with?('?')
  end

  def _shouting?(greeting)
    greeting.upcase =~ /[A-Z]/ && greeting == greeting.upcase
  end

  def _empty?(greeting)
    greeting.strip.empty?
  end
end
