class Bob
  def hey(comment)
    return "Fine. Be that way!" if comment.strip == ""
    return 'Woah, chill out!' if comment == comment.upcase && comment.downcase != comment
    return 'Sure.' if comment.end_with?("?")
    "Whatever."
  end
end
