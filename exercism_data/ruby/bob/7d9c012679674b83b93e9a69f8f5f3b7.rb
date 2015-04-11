class Bob
  def hey (phrase)
    if phrase.strip.empty?
      'Fine. Be that way!'
    elsif phrase == phrase.upcase and phrase != phrase.downcase
      'Woah, chill out!'
    elsif phrase.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end
end
