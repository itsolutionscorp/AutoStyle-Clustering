class String

  def normal_voice?
    if match(/^[A-Z]?[a-zA-Z\-,'\ \?]*[\.]/)
      true
    else
      false
    end
  end

  def shouting?
    if match(/^[0-9,A-Z\ \!\@\#\$\%\^\&\*\(\)]*[!]/) || match(/^[A-Z\ ]*$/)
      true
    else
      false
    end
  end

  def numbers_only?
    if match(/^[0-9\,\ ]*/)
      true
    else
      false
    end
  end

  def multiline_question?
    if match(/^[a-zA-Z,\d\ ]*[?]\n[\w\ ]+/)
      true
    else
      false
    end
  end

  def question?
    if match(/^[a-zA-Z,\d\ ]*[?]/)
      true
    else
      false
    end
  end

  def forceful_question?
    if match(/^[A-Z\ ]*[?]/)
      true
    else
      false
    end
  end

  def forceful?
    if !shouting? && match(/^[\w\s\']*[!]/)
      true
    else
      false
    end
  end

  def prattling?
    if match(/[(\!|\.|\?)\ ]{2,}[(A-Z|\z)]/)
      true
    else
      false
    end
  end

  def numeric?
    if match(/\d/)
      true
    else
      false
    end
  end
end

class Bob
  attr_accessor :statement

  def setup
    @statement = statement
  end

  def apathetic_response
    'Whatever.'
  end

  def shocked_response
    'Woah, chill out!'
  end

  def affirmative_response
    'Sure.'
  end

  def silent_response
    'Fine. Be that way!'
  end

  def hey(statement)
    return affirmative_response if statement.prattling?
    return apathetic_response if statement.multiline_question?
    return silent_response if statement.empty? || statement.match(/[\ ]{2,}/)

    if statement.normal_voice? || statement.forceful?
      apathetic_response
    elsif statement.shouting? || statement.forceful_question?
      shocked_response
    elsif statement.question?
      affirmative_response
    elsif statement.numbers_only?
      apathetic_response
    end
  end
end
