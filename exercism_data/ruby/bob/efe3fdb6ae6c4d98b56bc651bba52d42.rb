class Bob


  def hey(words)
    if words.to_s == '' || words =~ /^[*[:space:]$]/
      'Fine. Be that way!'
    elsif words.upcase == words
      'Woah, chill out!'
    elsif words[-1,1] == '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end
