class Bob

  def hey phrase
    return 'Fine. Be that way.' if unspoken? phrase
    return 'Sure.' if is_a_question? phrase
    return 'Woah, chill out!' if all_caps? phrase
    return 'Whatever.'
  end

  private

  def unspoken? phrase
    phrase.nil? || phrase.empty?
  end

  def is_a_question? phrase
    phrase.end_with? '?'
  end

  def all_caps? phrase
    phrase.upcase == phrase
  end

end
