class Bob

  def hey(words)
    if words.strip.empty?
      'Fine. Be that way!'
    elsif words.upcase == words && words.downcase != words
      'Whoa, chill out!'
    elsif words.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end

end
