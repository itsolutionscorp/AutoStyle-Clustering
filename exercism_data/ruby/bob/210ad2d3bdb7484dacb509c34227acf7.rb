class Bob
  def hey(sentence)
    if sentence.strip == ''
      "Fine. Be that way!" 
    elsif sentence == sentence.upcase && sentence =~ /[a-zA-Z]/
      "Woah, chill out!" 
    elsif sentence.end_with?("?")
      "Sure."
    else
      "Whatever."
    end
  end
end
