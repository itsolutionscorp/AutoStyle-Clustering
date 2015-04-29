def is_all_caps?(text)
  text.upcase == text
end

def is_question?(text)
  text.end_with?("?")
end

def is_blank?(text)
  text.strip.empty?
end

class Bob

  def hey(text)
    case
    when is_blank?(text)
      'Fine. Be that way!'
    when is_all_caps?(text)
      'Woah, chill out!'
    when is_question?(text)
      'Sure.'
    else
      'Whatever.'
    end
  end

end
