class Bob
  def hey comment
    if comment.strip.empty?
      "Fine. Be that way!"
    elsif comment == comment.upcase
      "Woah, chill out!"
    elsif comment.end_with? '?'
      "Sure."
    else
      "Whatever."
    end
  end
end
