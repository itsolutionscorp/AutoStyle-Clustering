class Bob
  def hey(words)
    words.gsub!("\n", "")
    if words.empty? || words.match(/^\s*$/)
      'Fine. Be that way!'
    elsif words == words.upcase
      'Woah, chill out!'
    elsif words.end_with?('?')
      'Sure.'
    else
      "Whatever."
    end  
  end
end
