class String

  def blank?
    self.to_s.strip == ""
  end

  def question?
    self.end_with? "?"
  end

  def all_caps?
    self == self.upcase
  end

end

module Responses
  TeenageResponse = {
    blank: "Fine. Be that way!",
    all_caps: "Woah, chill out!",
    question: "Sure.",
    default: "Whatever."
  }
end

class Bob
  include Responses

  def hey(string)
    string = string.to_s # sanity check to make sure we have one even if nil
    return TeenageResponse[:blank]    if string.blank?
    return TeenageResponse[:all_caps] if string.all_caps?
    return TeenageResponse[:question] if string.question?
    TeenageResponse[:default]
  end

end
