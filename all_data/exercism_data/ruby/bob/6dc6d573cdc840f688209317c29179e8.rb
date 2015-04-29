class Bob

  def hey phrase

    return 'Fine. Be that way.' if phrase.unspoken
    

    if phrase[-1] == '?'
      return 'Sure.'
    end

    if phrase.upcase == phrase
      return 'Woah, chill out!'
    end

    'Whatever.'
  end

  private

  def unspoken
    phrase.nil? || phrase.empty?
  end


end
