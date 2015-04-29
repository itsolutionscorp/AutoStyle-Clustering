class Bob
  def hey(say_what)
    line_end = say_what[-1,1]
    is_caps = say_what == say_what.upcase
    if (line_end == "!" && is_caps) || (line_end == "?" && is_caps && say_what =~ /[A-Za-z]/) || (line_end =~  /[A-Za-z]/ && is_caps)
      "Woah, chill out!" 
    elsif (line_end == ".") || (line_end == "!") || (line_end =~ /[[:digit:]]/ && line_end != ' ') || line_end =~ /[[:alpha:]]/
      "Whatever."
    elsif line_end == "?"
      "Sure."
    elsif line_end.nil? || line_end == ' '
      "Fine. Be that way!"
    end
  end
end
