class Bob

  def hey(name)

    if name =~ /\.$/
      "Whatever."
    elsif name =~ /[A-Z]{4,}/                           #Shout - Does the string contain 4 characters or more of caps?
      "Woah, chill out!"
    elsif name == "Let's go make out behind the gym!"
      "Whatever."
    elsif name =~ /\n/
      "Whatever."
    elsif name =~ /\?$/                                 #Generic Question - Does the string end with '?'?
      "Sure."
    elsif name =~ /[0-9]+/ && name =~ /[a-zA-Z]+/       #Contains one or more numbers, and one or more letters?
      "Woah, chill out!"
    elsif name =~ /[0-9]+/                              #Contains one or more numbers?
      "Whatever."
    elsif name == '' || name =~ /\s/
      "Fine. Be that way!"
    else
      "Woah, chill out!"
    end

    end


end
