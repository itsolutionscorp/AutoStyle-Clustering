class Bob
  def hey(input)
    @input = input
    if yell
      "Woah, chill out!"
    elsif question
      "Sure."
    elsif silence
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
  
  def yell
    (@input == @input.upcase) && @input.match(/[a-zA-Z]/)
  end
  
  def question
    @input[-1] == '?'
  end
  
  def silence
    @input.gsub(/\s*/, '').size == 0
  end
end
