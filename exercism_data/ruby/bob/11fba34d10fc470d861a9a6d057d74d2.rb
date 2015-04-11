class Bob
  def hey(say)
    say.strip! #remove all whitespaces, they don't change any rule
    return 'Fine. Be that way!' if say.empty?
    if say =~ /([a-zA-Z])+/
      return 'Woah, chill out!' if say == say.upcase # only ask for upcase version if say contains only words
    end
    return "Sure." if say.end_with?("?")
    return "Whatever."
  end
end
