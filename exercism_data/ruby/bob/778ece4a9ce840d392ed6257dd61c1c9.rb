class Bob
  def hey(arg)
    if arg == '' || arg.nil?
      "Fine. Be that way."
    else
      case arg
      when /\?\z/
        "Sure."
      when arg = arg.upcase
        "Woah, chill out!"
      when /\w+/
        "Whatever."
      end
    end
  end
end
