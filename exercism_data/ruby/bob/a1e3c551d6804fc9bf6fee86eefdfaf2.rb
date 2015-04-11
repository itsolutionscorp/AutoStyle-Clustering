class Bob
  def hey words
    return 'Woah, chill out!' if words == words.upcase && words =~ /[a-zA-Z]/
    return 'Sure.' if words[-1] == '?'
    return 'Fine. Be that way!' if words.empty? || words =~ /^\s+$/
    return 'Whatever.'
  end
end
