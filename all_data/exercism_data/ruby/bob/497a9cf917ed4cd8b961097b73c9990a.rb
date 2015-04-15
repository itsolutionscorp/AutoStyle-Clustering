class Bob 

  def hey(string)
    return 'Fine. Be that way!' if if_empty?(string)
    if if_only_numbers?(string)
      if if_question?(string)
        return 'Sure.'
      end
      return 'Whatever.'
    end
    return 'Woah, chill out!' if if_shouting?(string)
    return 'Sure.' if if_question?(string)
    return 'Whatever.'
  end 

  def if_empty?(string)
    string.strip == ""
  end

  def if_question?(string)
    string[-1] == '?'
  end

  def if_shouting?(string)
    string == string.upcase
  end

  def if_only_numbers?(string)
    string.delete("a-zA-Z") == string
  end
end
