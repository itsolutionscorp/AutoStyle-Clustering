class Bob
  def hey phrase
    if is_yelling? phrase
      "Woah, chill out!"
    elsif is_question? phrase
      "Sure."
    elsif is_silence? phrase
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  private
  def is_yelling? phrase
    phrase.gsub(/\p{^Alpha}/, '') == phrase.gsub(/\p{^Alpha}/, '').upcase && phrase.gsub(/\p{^Alpha}/, '') != ''
  end

  def is_question? phrase
    phrase.end_with? '?'
  end
  
  def is_silence? phrase
    phrase =~ /^\s+$/ || phrase == ''
  end
end
