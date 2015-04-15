class Bob
  def hey something
    case analyse something
    when :yelling then "Woah, chill out!"
    when :question then "Sure."
    when :silence then "Fine. Be that way!"
    else "Whatever."
    end
  end

  def analyse str
    return :yelling if is_yelling? str
    return :question if is_question? str
    return :silence if is_silent? str
    :unknown
  end

  def is_silent? str
    str.strip.empty?
  end

  def is_question? str
    str.end_with? "?"
  end

  def is_yelling? str
    str != str.downcase && str == str.upcase
  end
end
