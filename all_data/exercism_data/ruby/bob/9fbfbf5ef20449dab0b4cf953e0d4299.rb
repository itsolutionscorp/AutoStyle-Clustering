class Bob
  def hey(text)
    if not text or text.empty?
      "Fine. Be that way."
    elsif text.eql?(text.upcase)
      "Woah, chill out!"
    elsif text.end_with?("?") 
      "Sure."
    else
      "Whatever."
    end
  end
end
