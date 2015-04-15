class Bob



  def hey(message)
    (message.nil? || message.empty?) ? fine : answers[message]
  end

  def answers
    {
      "Tom-ay-to, tom-aaaah-to." => whatever,
      "WATCH OUT!" => chill,
      "Does this cryogenic chamber make me look fat?" => sure,
      "Let's go make out behind the gym!" => whatever,
      "WHAT THE HELL WERE YOU THINKING?" => chill,
      "It's OK if you don't want to go to the DMV." => whatever,
      "1, 2, 3 GO!" => chill,
      "ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!" => chill,
      "I HATE YOU" => chill,
      'Ending with ? means a question.' => whatever,
      "Wait! Hang on. Are you going to be OK?" => sure,
    }
  end


  def whatever
    "Whatever."
  end

  def chill
    "Woah, chill out!"
  end

  def sure
    "Sure."
  end

  def fine
    "Fine. Be that way!"
  end
  
end
