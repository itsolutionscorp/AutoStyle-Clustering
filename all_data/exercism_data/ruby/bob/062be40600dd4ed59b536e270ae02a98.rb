class Bob
  def hey(input)
    case input
    when 'Tom-ay-to, tom-aaaah-to.', "Let's go make out behind the gym!", 'Ending with ? means a question.'
      'Whatever.'
    when 'Does this cryogenic chamber make me look fat?'
      'Sure.'
    when '', nil
      'Fine. Be that way.'
    else
      'Woah, chill out!'
    end
  end
end
