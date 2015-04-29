class Bob

  def hey remark
    case
    when is_yelling?(remark)
      "Whoa, chill out!"
    when is_question?(remark)
      "Sure."
    when is_nothing?(remark)
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  # ends with '?'
  def is_question? remark
    remark.end_with? "?"
  end

  # handle yelling at him (means all caps and only letters)
  def is_yelling? remark
    remark =~ /[A-Z]/ && remark == remark.upcase
  end

  # only whitespace
  def is_nothing? remark
    remark.strip.empty?
  end

end
