class Bob
  def hey(arg)
    case arg
    when arg == '' || arg.nil?
      "Fine. Be that way."
    when /\?\z/
      "Sure."
    when arg = arg.upcase
      "Woah, chill out!"
    when /\w+/
      "Whatever."
    end
  end
end
