class Bob
  
  def only_number(arg)
    arg.gsub(", ", "").strip
  end

  def hey(arg)
    if arg == nil || arg.strip.empty?
      "Fine. Be that way!"
    elsif arg == arg.upcase && only_number(arg) =~ /\s/
      "Woah, chill out!"
    elsif arg.end_with?("?")
      "Sure."
    else
      "Whatever."
    end
  end 
end
