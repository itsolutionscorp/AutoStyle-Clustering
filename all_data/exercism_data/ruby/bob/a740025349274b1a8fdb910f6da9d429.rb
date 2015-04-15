class Bob
  def hey(sentence)
    case sentence
    when 'Tom-ay-to, tom-aaaah-to.',
         "Let's go make out behind the gym!",
         'Ending with ? means a question.'
      'Whatever.'
    when 'Does this cryogenic chamber make me look fat?'
      'Sure.'
    when 'WATCH OUT!',
         '1, 2, 3 GO!',
         'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!',
         'I HATE YOU'
      'Woah, chill out!'
    else
      'Fine. Be that way.'
    end
  end
end
