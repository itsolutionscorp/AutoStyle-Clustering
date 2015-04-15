class Bob
  def hey(message)
    case message
     when /\A[\s\r\n]*\Z/ # empty string or spaces
       "Fine. Be that way!"
     when /\A([^a-z]+[A-Z]+[^a-z]+)*\Z/ # Yelling (sans question)
	"Woah, chill out!"
      when /\A.*?\?$\Z/ # Any Question
        "Sure."
      else
        "Whatever."
    end
  end
end
