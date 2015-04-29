class String
  def normal_voice?
    match(/^[A-Z]?[a-zA-Z\-,'\ \?]*[\.]/) ? true : false
  end

  def shouting?
    match(/^[0-9,A-Z\ \!\@\#\$\%\^\&\*\(\)]*[!]/) || match(/^[A-Z\ ]*$/) ? true : false
  end

  def numbers_only?
    match(/^[0-9\,\ ]*/) ? true : false
  end

  def multiline_question?
    match(/^[a-zA-Z,\d\ ]*[?]\n[\w\ ]+/) ? true : false
  end

  def question?
    match(/^[a-zA-Z,\d\ ]*[?]/) ? true : false
  end

  def forceful_question?
    match(/^[A-Z\ ]*[?]/) ? true : false
  end

  def forceful?
    !shouting? && match(/^[\w\s\']*[!]/) ? true : false
  end

  def prattling?
    match(/[(\!|\.|\?)\ ]{2,}[(A-Z|\z)]/) ? true : false
  end

  def numeric?
    match(/\d/) ? true : false
  end
end

class Bob
  attr_accessor :statement

  def setup
    @statement = statement
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

  private

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
end
