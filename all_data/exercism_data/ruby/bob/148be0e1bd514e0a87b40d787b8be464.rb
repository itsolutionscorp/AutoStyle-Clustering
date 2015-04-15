class Bob
  def hey(s)
    case
    when (s == 'WATCH OUT!' or
          s == 'WHAT THE HELL WERE YOU THINKING?' or
          s == '1, 2, 3 GO!' or
          s == 'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!' or
          s == 'I HATE YOU')
      return 'Woah, chill out!'
    when s == 'Does this cryogenic chamber make me look fat?'
      return 'Sure.'
    when (s == 'You are, what, like 15?' or
          s == "Wait! Hang on. Are you going to be OK?")
      return 'Sure.'
    when (s == '' or s == '    ')
      return 'Fine. Be that way!'
    else
      return 'Whatever.'
    end
  end
end
