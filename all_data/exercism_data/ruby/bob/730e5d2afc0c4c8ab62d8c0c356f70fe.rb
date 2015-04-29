class Bob

  def hey s
    if isEmpty s then "Fine. Be that way!"
    elsif shout s then  'Woah, chill out!'
    elsif isQuestion s then 'Sure.'
    else 'Whatever.'
    end
  end

  def isEmpty s
    s.strip.empty?
  end

  def shout s
    s =~ /[A-Z]/ and s.upcase == s
  end

  def isQuestion s
    s[-1] == "?"
  end

end
