class Bob
  def hey(msg)
    return fine if empty?(msg)
    return chill_out if forcefully_question?(msg)
    return chill_out if forcefully_talking?(msg)
    return sure if question?(msg)
    return whatever
  end

  private

  def forcefully_question?(msg)
    return true if upcase?(msg) and question?(msg)
    return false
  end

  def forcefully_talking?(msg)
    return true if upcase?(msg) and shouting?(msg)
    return true if upcase?(msg)
    return false
  end

  def empty?(msg)
    return true if msg.empty?

    empty = true

    msg.split("").each do |c|
      empty = false if c != ' '
    end

    return true if empty

    return false
  end

  def upcase?(msg)
    special_cases = [' ', '!', '?']

    upcase = true

    msg.split("").each do |c|
      upcase = false if c != c.upcase and !special_cases.include?(c)
    end

    upcase
  end

  def question?(msg)
    return true if msg[-1] == "?"
    return false
  end
  
  def shouting?(msg)
    return true if msg[-1] == "!"
    return false
  end

  def whatever
    'Whatever.'
  end

  def sure
    'Sure.'
  end

  def chill_out
    'Woah, chill out!'
  end

  def fine
    "Fine. Be that way!"
  end
end
