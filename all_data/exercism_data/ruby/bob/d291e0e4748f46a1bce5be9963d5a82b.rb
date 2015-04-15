class Bob
  def hey(what)
    case what
    when 'WATCH OUT!', 
         '1, 2, 3 GO!',
         'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!', 
         'I HATE YOU'
      'Woah, chill out!'
    when 'Does this cryogenic chamber make me look fat?'
      'Sure.'
    when '', 
         nil
      'Fine. Be that way.'
    else
      'Whatever.'
    end
  end
end
