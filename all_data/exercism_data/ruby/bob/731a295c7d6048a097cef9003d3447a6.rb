class Bob

  def hey words
    if question? words
      'Sure.'
    elsif yelling? words      
      'Woah, chill out!'
    elsif  words.lstrip.empty?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  def question? word
    /[^[[:upper:]]]\?$/ === word.strip.gsub("\n", " ")
   # word.end_with? "?"
  end

  def yelling? word
    /[[:upper:]][^[[:lower:]]]\!|\?$/ === word.strip.gsub("\n", " ")    
  end

  def blank? word
    /[^.{1,}][[:blank:]]/ === word
  end
  
end
