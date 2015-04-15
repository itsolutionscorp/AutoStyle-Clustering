class Bob
  def hey(text)
    if text =~ /\A *\Z/
      "Fine. Be that way!"      
    elsif text.upcase == text
      'Woah, chill out!'    
    elsif text.end_with?('?')
      'Sure.'
    else
      "Whatever."
    end
  end
end
