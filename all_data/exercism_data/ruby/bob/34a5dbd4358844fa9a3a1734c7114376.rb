class Bob
  def hey(greeting)
    responses = [
      {evaluation: method(:_empty?), response: 'Fine. Be that way!'},
      {evaluation: method(:_shouting?), response: 'Woah, chill out!'},
      {evaluation: method(:_question?), response: 'Sure.'},
      {evaluation: method(:_whatever?), response: 'Whatever.'}
    ]

    responses.find { |response_maps| response_maps[:evaluation].call(greeting) }[:response]
  end

  private

  def _question?(greeting)
    greeting.end_with?('?')
  end

  def _shouting?(greeting)
    greeting.upcase =~ /[A-Z]/ && greeting == greeting.upcase
  end

  def _empty?(greeting)
    greeting.strip.empty?
  end

  def _whatever?(greeting)
    true
  end
end
