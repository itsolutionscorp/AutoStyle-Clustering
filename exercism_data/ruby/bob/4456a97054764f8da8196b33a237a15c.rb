class Bob
  def hey(comment)
    return "Fine. Be that way!" if silent?(comment)
    return "Woah, chill out!" if yelling?(comment)
    return "Sure." if question?(comment)
    "Whatever."
  end

  private

  def silent?(comment)
    comment.nil? || comment.rstrip.empty?
  end

  def yelling?(comment)
    comment == comment.upcase
  end

  def question?(comment)
    comment.end_with?("?")
  end
end
