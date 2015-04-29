class Bob
  def hey(s)
    is_yelling 	= ->(x) { /\A[^a-z]*[A-Z]+[^a-z]*\z/ === x } 
    is_question = ->(x) { x[-1] === '?'  	}
    is_silent 	= ->(x) { x.strip.empty? 	}

    case s
    when is_yelling 
      "Woah, chill out!"
    when is_question 
      "Sure."
    when is_silent 
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
