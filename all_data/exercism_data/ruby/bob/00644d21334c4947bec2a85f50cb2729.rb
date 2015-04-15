class Bob

  def hey phrase
    phrase.strip!

    if is_yell?(phrase)
      'Woah, chill out!'
    elsif is_question?(phrase)
      'Sure.'
    elsif phrase.empty?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

private
  def is_yell? phrase
    clean_phrase = phrase.gsub(/[%^*@#$(*^,\?\s]/ , '').gsub(/[1-9]/, '')
    !clean_phrase.empty? && clean_phrase == clean_phrase.upcase
  end

  def is_question? phrase
    phrase[-1] == "?"
  end
end
