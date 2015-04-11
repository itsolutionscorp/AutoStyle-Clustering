class Bob
  UPPERCASE_SPACE_AND_PUNCT = /[[:upper:]]|[[:digit:]]|[[:punct:]]|[[:space:]]/

  def hey(what)
    case what
    when /^\s*$/
      'Fine. Be that way!'
    when /^#{UPPERCASE_SPACE_AND_PUNCT}+$/o
      'Woah, chill out!'
    when /\?$/
      'Sure.'
    when nil
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
