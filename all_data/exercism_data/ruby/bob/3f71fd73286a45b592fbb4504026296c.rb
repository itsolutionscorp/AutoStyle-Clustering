class Bob

  def hey(phrase)
    # return phrase
    # puts "#{phrase}"
    return test(phrase)
    # puts "#{response}"
  end

  def test(phrase)
    msg = 'Whatever.'

    if question(phrase)
      msg = 'Sure.'
    end
    if all_caps(phrase)
      msg = 'Woah, chill out!'
    end
    if is_blank(phrase)
      msg = 'Fine. Be that way!'
    end

    return msg
  end

  def is_blank(phrase)
    phrase.strip.empty?
  end

  def all_caps(phrase)
    phrase == phrase.upcase
  end

  def question(phrase)
    location = phrase.rindex('?')
    if !location
      return false
    end
    location + 1 == phrase.length
  end

end
