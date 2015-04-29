class Bob
  RESPONSE_TO = {
    question: "Sure.",
    yell: "Woah, chill out!",
    blank: "Fine. Be that way.",
    default: "Whatever."
  }.freeze

  def hey(str)
    RESPONSE_TO[key_for(str)]
  end

  private

  def key_for(str)
    return :blank    if empty?(str)
    return :yell     if yelling?(str)
    return :question if question_in?(str)
    :default
  end

  def question_in?(str)
    str[-1] == "?"
  end

  def yelling?(str)
    str.to_s.upcase == str.to_s
  end

  def empty?(str)
    str.nil? || str == ""
  end
end
