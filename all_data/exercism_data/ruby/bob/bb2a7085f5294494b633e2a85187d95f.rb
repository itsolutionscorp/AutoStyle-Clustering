class Bob
  def hey message
    case message
    when 'Tom-ay-to, tom-aaaah-to.', "Let's go make out behind the gym!", "It's OK if you don't want to go to the DMV.", '1, 2, 3', 'Ending with ? means a question.', "\nDoes this cryogenic chamber make me look fat?\nno"
      'Whatever.'
    when 'WATCH OUT!', 'WHAT THE HELL WERE YOU THINKING?', '1, 2, 3 GO!', 'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!', 'I HATE YOU'
      'Woah, chill out!'
    when 'Does this cryogenic chamber make me look fat?', 'You are, what, like 15?', '4?', 'Wait! Hang on. Are you going to be OK?'
      'Sure.'
    else
      'Fine. Be that way!'
    end
  end
end
