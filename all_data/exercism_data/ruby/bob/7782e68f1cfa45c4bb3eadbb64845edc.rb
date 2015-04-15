class Bob

  def hey s
    if empty? s then "Fine. Be that way!"
    elsif shout? s then  'Woah, chill out!'
    elsif question? s then 'Sure.'
    else 'Whatever.'
    end
  end

  def empty? s
    s.strip.empty?
  end

  def shout? s
    s =~ /[A-Z]/ and s.upcase == s
  end

  def question? s
    s.end_with? "?"
  end

end
