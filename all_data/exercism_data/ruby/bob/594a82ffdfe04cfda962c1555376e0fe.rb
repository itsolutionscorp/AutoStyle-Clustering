class Bob
  def hey(phrase)
    phrase.gsub!(/\n/, "")
    if /\?$/.match(phrase)
      if /^[A-Z]{2,}/.match(phrase)
        'Woah, chill out!'
      else
        'Sure.'
      end
    elsif /^[A-Z]+\s?[A-Z]{2,}/.match(phrase) || /[A-Z]+\!$/.match(phrase)
      'Woah, chill out!'
    elsif /^\s*$/.match(phrase)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
