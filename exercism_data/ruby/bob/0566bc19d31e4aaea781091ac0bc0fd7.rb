class Bob 

  def hey(string)
    return 'Fine. Be that way!' if is_empty(string)
    if is_only_numbers(string)
      if is_question(string)
        return 'Sure.'
      end
      return 'Whatever.'
    end
    return 'Woah, chill out!' if is_shouting(string)
    return 'Sure.' if is_question(string)
    return 'Whatever.'
  end 

  def is_empty(string)
    string.strip == ""
  end

  def is_question(string)
    string[-1] == '?'
  end

  def is_shouting(string)
    string == string.upcase
  end

  def is_only_numbers(string)
    string.delete("a-zA-Z") == string
  end
end
