class Bob
  def hey(text)
    if text.strip.empty?
      "Fine. Be that way!"
    elsif text.eql?(text.upcase) && text.count('A-Za-z')>0
      "Whoa, chill out!"
    elsif text.end_with?('?')
      "Sure."
    else
      "Whatever."
    end
  end
end
