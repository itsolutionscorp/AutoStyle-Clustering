class Bob
  def hey(phrase)
    case phrase
      when silent?
        "Fine. Be that way!"
      when all_caps?
        "Woah, chill out!"
      when question?
        "Sure."
      else
        "Whatever."
      end
  end
  
  protected
  def silent?
    lambda { |phrase| phrase =~ /\A\s*\Z/m }
  end
  
  def all_caps?
    lambda { |phrase| phrase.upcase == phrase && phrase =~ /[A-Z]+/ }
  end
  
  def question?
    lambda { |phrase| phrase[-1] == "?" }
  end
end
