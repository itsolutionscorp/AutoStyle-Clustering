class Bob
  def hey words
    if words == words.upcase && words =~ /[A-Z]/ 
      'Woah, chill out!'
    elsif words.end_with?('?')
      'Sure.'
    elsif words.strip.empty? 
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
