class Bob

  def hey phrase

    if phrase.nil? || phrase.empty?
      return 'Fine. Be that way.'
    end

    if phrase[-1] == '?'
      return 'Sure.'
    end

    if phrase.upcase == phrase
      return 'Woah, chill out!'
    end

    'Whatever.'
  end

end
