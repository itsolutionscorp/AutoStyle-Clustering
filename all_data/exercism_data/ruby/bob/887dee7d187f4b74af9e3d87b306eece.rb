class Bob
  def hey(word)
    return 'Fine. Be that way!' if word.strip.size.zero?
    if word == word.upcase
      return 'Woah, chill out!'
    elsif word.end_with?('?')
      return 'Sure.'
    else
      return 'Whatever.'
    end
  end
end
