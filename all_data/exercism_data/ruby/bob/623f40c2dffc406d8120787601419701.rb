class Bob
  RESPONSES = {
    shouting:  "Whoa, chill out!",
    question:  "Sure.",
    silence:   "Fine. Be that way!",
    statement: "Whatever."
  }

  def hey(remark)
    RESPONSES[classify(remark)]
  end

  private
  def classify(remark)
    if remark =~ /[A-Z]/ && remark !~ /[a-z]/
      # at least one uppercase, and no lowercase
      :shouting
    elsif remark =~ /\?\Z/
      # not shouting and ends (for real, not just a line) with a question mark
      :question
    elsif remark !~ /\S/
      # no non-whitespace characters = silence
      :silence
    else
      # everything else
      :statement
    end
  end
end
