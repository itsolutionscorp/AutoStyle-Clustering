class Bob
  def hey greeting
    return "Woah, chill out!" if greeting == greeting.upcase && greeting.match(/[A-Z]/)
    return "Sure." if greeting.end_with?("?")
    return 'Fine. Be that way!' if greeting.strip == ""
    return "Whatever."
  end
end
