class Bob

  def hey(blah)
    if all_caps? blah
      return "Woah, chill out!"
    elsif question? blah
      return "Sure."
    elsif blank? blah
      return "Fine. Be that way!"
    else
      return "Whatever."
    end
  end

  private

  def question? sentence
    sentence.end_with? '?'
  end

  def blank? sentence
    sentence.strip.empty?
  end

  def all_caps? sentence
    (sentence =~ /[A-Z]/) and not (sentence =~ /[a-z]/)
  end

end
