class Bob
  attr_reader :phrase

  def hey(input_phrase)
    @phrase = input_phrase
    response
  end

  private

  def response
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
    phrase.split == Array.new
  end

end
