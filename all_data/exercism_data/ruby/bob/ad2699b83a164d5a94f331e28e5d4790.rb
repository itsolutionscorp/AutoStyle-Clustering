class Bob
  def initialize(response_klass = TeenagerResponse)
    @response_class = response_klass
  end

  def hey(input_phrase)
    @response_class.new(input_phrase).to_s
  end
end

class TeenagerResponse
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def to_s
    if question? && (only_digits? || !uppercase?)
      'Sure.'
    elsif whitespace?
      'Fine. Be that way!'
    elsif uppercase? && non_digits?
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end

  private

  def question?
    phrase.end_with?('?')
  end

  def uppercase?
    phrase.upcase == phrase
  end

  def non_digits?
    phrase_without_comma_spaces.match(/\D/)
  end

  def only_digits?
    phrase_without_comma_spaces.match(/\d/)
  end

  def phrase_without_comma_spaces
    phrase.gsub(', ', String.new)
  end

  def whitespace?
    phrase.split.empty?
  end

end
