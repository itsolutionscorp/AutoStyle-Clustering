class Bob
  def hey(text)
    text.gsub!(/\s/, '')

    return 'Fine. Be that way!' if text.length == 0
    return 'Whoa, chill out!' if yelling?(text)
    return 'Sure.' if text[-1] == '?'
    return 'Whatever.'
  end

  private
  def yelling?(text)
    letters = text.gsub(/[^a-zA-Z]/, '')

    return false if letters.length == 0
    return letters == letters.upcase
  end
end
