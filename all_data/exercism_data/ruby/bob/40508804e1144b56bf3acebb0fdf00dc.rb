class Bob

  SILENCE   = -> s { s.strip.empty? }
  SHOUTS    = -> s { s.upcase == s  }
  QUESTION  = -> s { s[-1] == '?'   }

  # Bob is a lackadaisical teenager. In conversation, his responses are very limited.
  # 
  # Bob answers 'Sure.' if you ask him a question.
  # 
  # He answers 'Woah, chill out!' if you yell at him (ALL CAPS).
  # 
  # He says 'Fine. Be that way!' if you address him without actually saying anything.
  # 
  # He answers 'Whatever.' to anything else.
  def hey s
    # just see which criteria matches first
    case s
    when SILENCE  ; "Fine. Be that way!"
    when SHOUTS   ; "Woah, chill out!"
    when QUESTION ; "Sure."
    else          ; "Whatever."
    end
  end

end
