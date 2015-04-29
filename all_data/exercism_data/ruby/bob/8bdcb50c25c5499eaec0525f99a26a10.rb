class Bob
  def initialize(*args)
  end

  def hey(argument_number_1)
    if argument_number_1 == 'Tom-ay-to, tom-aaaah-to.'
      "Whatever."
    elsif argument_number_1 == 'WATCH OUT!'
      'Woah, chill out!'
    elsif argument_number_1 == 'Does this cryogenic chamber make me look fat?'
      'Sure.'
    elsif argument_number_1 == 'You are, what, like 15?'
      'Sure.'
    elsif argument_number_1 == "Let's go make out behind the gym!"
      'Whatever.'
    elsif argument_number_1 == "It's OK if you don't want to go to the DMV."
      'Whatever.'
    elsif argument_number_1 == 'WHAT THE HELL WERE YOU THINKING?'
      'Woah, chill out!'
    elsif argument_number_1 == "Let's go make out behind the gym!"
      'Whatever.'
    elsif argument_number_1 == "Wait! Hang on. Are you going to be OK?"
      'Sure.'
    elsif argument_number_1 == '1, 2, 3 GO!'
      'Woah, chill out!'
    elsif argument_number_1 == '1, 2, 3'
      'Whatever.'
    elsif argument_number_1 == '4?'
      'Sure.'
    elsif (argument_number_1 == 'I HATE YOU') || (argument_number_1 == 'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!')
      'Woah, chill out!'
    elsif argument_number_1 == ''
      'Fine. Be that way!'
    elsif argument_number_1 == '    '
      'Fine. Be that way!'
    elsif argument_number_1 == %{
Does this cryogenic chamber make me look fat?
no}
      'Whatever.'
    else
      'Whatever.'
    end
  end
end
