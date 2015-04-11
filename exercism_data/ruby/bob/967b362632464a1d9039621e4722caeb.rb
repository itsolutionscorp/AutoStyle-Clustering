# encoding: utf-8

# Sentence helper class
class Sentence
  def initialize(string_value)
    @string_value = string_value
  end

  # Call with an array of symbols.  Each one will be evaluated
  # in order as a test case (e.g., :silence -> silence?).
  # The first match will be returned.  If no match is found,
  # :other will be returned
  def interpret_with(interpretations)
    interpretations.each do |interpretation|
      return interpretation if send(interpretation.to_s + '?')
    end
  end
  
  def silence?
    @string_value.strip.empty?
  end

  def question?
    @string_value[-1] == '?'
  end

  def shout?
    # apparently Bob knows if you spoke only in letters
    alphas = @string_value.tr('^A-Za-z', '')
    !alphas.empty? && (alphas.upcase == alphas)
  end

  def other?
    true
  end
end

# A generic teenager that can interpret sentences
# Initialize with a hash of responses in the order
# that they should be considered, where the hash key
# is a symbol corresponding to a test case on Sentence and
# the hash value is the corresponding string response.
# The default response should have a key of :other.
class Teenager
  def initialize(responses)
    @responses = responses
  end

  def hey(sentence)
    @responses[interpret sentence]
  end

  private

  def interpret(sentence_as_string)
    Sentence.new(sentence_as_string).interpret_with(@responses.keys)
  end
end

# implementation of Bob for exercism exercise
class Bob < Teenager
  Responses = {
    silence:  'Fine. Be that way!',
    shout:    'Woah, chill out!',
    question: 'Sure.',
    other:    'Whatever.'
  }

  def initialize
    super(Responses)
  end
end
