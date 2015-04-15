class Bob

  attr_reader :phrase

  def hey(phrase)
    @phrase = phrase

    if blank?
      "Fine. Be that way!"
    elsif question?
      "Sure."
    elsif shouted? && !all_numbers?
      "Woah, chill out!"
    else
      "Whatever."
    end

  end

  private

  def shouted?
    return false if phrase.match(/\d+\?$/)
    phrase.upcase == phrase
  end

  def question?
    return false if shouted?
    phrase.match(/\?$/) && !phrase.match(/\n/)
  end

  def all_numbers?
    phrase.match(/(\d+,\W)+\d+$/)
  end

  def blank?
    phrase.strip == ""
  end

end
