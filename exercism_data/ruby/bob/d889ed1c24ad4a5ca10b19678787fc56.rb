class Bob
  
  def hey(phrase)
    phrase.gsub!("\n","")
    if angry? phrase 
      "Whoa, chill out!"
    elsif question? phrase
      "Sure."
    elsif silence? phrase
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  def question?(phrase)
    phrase =~/[a-z].*\?$/x or phrase =~ /[^A-Z].*\?$/x
  end

  def silence?(phrase)
    phrase =~ /^\s*$/
  end

  def angry?(phrase)
    phrase =~ /[A-Z]/ and phrase == phrase.upcase
  end
end
