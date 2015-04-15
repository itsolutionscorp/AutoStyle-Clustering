class Bob

  def remove_spaces(phrase)
    phrase.gsub /\s+/, ''
  end

  def remove_punctuation(phrase)
    phrase.gsub /[^a-zA-Z 0-9]+/, ''
  end

  def only_numbers(phrase)
    /^\d+$/.match remove_spaces remove_punctuation phrase
  end

  def hey(phrase)
    yelling = phrase == phrase.upcase # /[A-Z\s]*/.match(phrase)
    question = /[?]\z/.match(phrase)

    if remove_spaces(phrase).size == 0
      "Fine. Be that way!"
    elsif only_numbers phrase
      if question
        'Sure.'
      else
        'Whatever.'
      end
    elsif yelling
      'Whoa, chill out!'
    elsif question
      'Sure.'
    else
      'Whatever.'
    end
  end
end
