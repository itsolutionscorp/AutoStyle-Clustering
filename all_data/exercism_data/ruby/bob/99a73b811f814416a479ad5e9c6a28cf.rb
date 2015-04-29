class Bob
  def hey(words = nil)
    if(words.nil? || words.strip.empty?)
      return 'Fine. Be that way!'
    elsif(words == words.upcase && words.upcase.count("A-Z")>0)
      return 'Woah, chill out!'
    elsif(words.end_with?("?"))
      return 'Sure.'
    else
      return 'Whatever.'
    end
  end
end
