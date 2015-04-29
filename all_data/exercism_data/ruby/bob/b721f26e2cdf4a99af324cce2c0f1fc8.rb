class Bob
  stating = /^[A-Z][a-z\s-]+['?,][a-z\s-]+[.!]$/
    shouting = /[A-Z\s]*[1-9,\W\s]*[A-Z\s]+[1!]*$/
    asking = /[A-Z][a-z\s]+[?]$/
    silence = /[\s]?/

    def hey(arg)
      case arg
      when stating
        "Whatever."
      when shouting
        "Woah, chill out!"
      when asking
        "Sure."
      when silence, nil
        "Fine. Be that way."
      end
    end
end
