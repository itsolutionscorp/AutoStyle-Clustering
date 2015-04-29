class Bob
  def hey(comment)
    if shouting?(comment)
      "Whoa, chill out!"
    elsif question?(comment)
      "Sure."
    elsif silent?(comment)
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  private

  def shouting?(comment)
    comment == comment.upcase && comment =~ /[A-Z]/
  end

  def question?(comment)
    comment.end_with?("?")
  end

  def silent?(comment)
    comment.strip.empty?
  end
end
