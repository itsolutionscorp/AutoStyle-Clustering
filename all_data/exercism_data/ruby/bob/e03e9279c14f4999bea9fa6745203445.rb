class Bob

  def hey(words)
    if words.to_s == ''
      return 'Fine. Be that way!'
    elsif allcaps(words)
      'Woah, chill out!'
    elsif words.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end

  private
  def allcaps(words)
    words == words.upcase
  end
end
