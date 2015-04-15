class Responder
  attr_accessor :phrase_types

  def initialize(phrase_types)
    self.phrase_types = phrase_types
  end

  def response_for(input)
    input ||= ''

    phrase_types.each do |comparison, response|
      return response if comparison === input
    end
  end
end

class Bob
  attr_accessor :responder

  def phrase_types
    {
      ->(input) { input.strip.empty? }    => 'Fine. Be that way!',
      ->(input) { input == input.upcase } => 'Woah, chill out!',
      ->(input) { input.end_with? '?' }   => 'Sure.',
      ->(input) { true }                  => 'Whatever.'
    }
  end

  def initialize
    self.responder = Responder.new(phrase_types)
  end

  def hey(input)
    responder.response_for input
  end
end
