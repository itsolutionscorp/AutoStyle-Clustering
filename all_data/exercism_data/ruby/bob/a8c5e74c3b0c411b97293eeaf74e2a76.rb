class Bob

  def hey(input)
    if silence? input
      'Fine. Be that way!'
    elsif yelling? input
      'Woah, chill out!'
    elsif question? input
      'Sure.'
    else
      'Whatever.'
    end
  end

  def silence?(input)
    not(input.split("").find{|letter|letter!=" " })
  end

  def yelling?(input)
    sentence_is_all_caps? input
  end

  def sentence_is_all_caps?(input)
    not(input.split(" ").find{|word|not word_is_all_caps?(word)})
  end

  def word_is_all_caps?(word)
    not(word.split("").find{|letter|letter.upcase!=letter})
  end

  def question?(input)
    input[-1]=='?'
  end

end
