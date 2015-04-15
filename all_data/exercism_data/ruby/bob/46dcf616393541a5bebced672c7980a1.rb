class Bob
  def hey(something)
    word = word_without_white_signs(something)
    if(not talking_to_me?(word))
      'Fine. Be that way!'
    elsif (shouting? (word))
      'Woah, chill out!'
    elsif (question?(word))
      'Sure.'
    else 'Whatever.'
    end
  end

  private
  def question? (something)
    something[-1, 1] == '?'
  end

  def shouting? (something)
    something == something.upcase and something != something.downcase
  end

  def talking_to_me? (something)
    something != ''
  end

  def word_without_white_signs (something)
    something.gsub(/\s+/,'')
  end
end
