class Bob
  def hey(str)
    unless str.nil?
      punctuation = str[-1]
      yelling = (str.upcase == str)
    end
    if str.nil? || str.empty?
      return "Fine. Be that way."
    elsif punctuation == '.' || (punctuation == '!' && !yelling)
      return "Whatever."
    elsif yelling
      return "Woah, chill out!"
    else punctuation == '?'
      return "Sure."
    end
  end
end
