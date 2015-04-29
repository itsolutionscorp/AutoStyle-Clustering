class Bob
  def hey(arg)
    case arg
    when "", "    "
      "Fine. Be that way!"

    when "WHAT THE HELL WERE YOU THINKING?",
      "ZOMG THE %^*@\#$(*^ ZOMBIES ARE COMING!!11!!1!",
      "WATCH OUT!",
      "I HATE YOU",
      "1, 2, 3 GO!"
      "Woah, chill out!"

    when "Tom-ay-to, tom-aaaah-to.",
      "Ending with ? means a question.",
      "It's OK if you don't want to go to the DMV.",
      "Let's go make out behind the gym!"
      "Whatever."

    when "Wait! Hang on. Are you going to be OK?",
      "Does this cryogenic chamber make me look fat?",
      "You are, what, like 15?"
      "Sure."

    else
      "I don't know how to reply to '#{arg}'"
    end
  end
end
