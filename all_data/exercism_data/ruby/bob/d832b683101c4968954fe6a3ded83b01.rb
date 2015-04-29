class Bob
  def hey(phrase)
    if (phrase.nil? or phrase.empty?)
      return 'Fine. Be that way.'
    end

    if (phrase.upcase == phrase)
      return 'Woah, chill out!'
    end

    if (phrase[-1] == '?')
      return 'Sure.'
    end

    return 'Whatever.'
  end
end
