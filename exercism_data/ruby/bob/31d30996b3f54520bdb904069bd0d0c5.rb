class Bob
  def hey(words)

    if nothing(words)
      'Fine. Be that way!'
    elsif yelling(words)
      'Woah, chill out!'
    elsif questioning(words)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def nothing(words)
    words.strip.empty?
  end

  def yelling(words)
    words.upcase == words && words.downcase != words
  end

  def questioning(words)
    words.end_with?('?')
  end
end
