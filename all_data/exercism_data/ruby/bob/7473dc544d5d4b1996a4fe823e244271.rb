class Bob
  def initialize
  end

  def hey phrase
    if phrase.upcase == phrase && hasAlpha?(phrase) 
      "Woah, chill out!"
    elsif phrase.end_with? '?'
      'Sure.'      
    elsif phrase.strip == ""
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  def hasAlpha? string
    string.match(/[[:alpha:]]/) != nil
  end
end
