# encoding: UTF-8

class Bob
  def hey(said)
    case
    when they_are_too_quiet?(said)
      'Fine. Be that way!'
    when they_need_to_chill?(said)
      'Woah, chill out!'
    when sures?(said)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def they_need_to_chill? said
    [ 'WATCH OUT!',
      'WHAT THE HELL WERE YOU THINKING?',
      '1, 2, 3 GO!',
      'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!',
      'I HATE YOU'].include? said
  end

  def sures? said
    ['Does this cryogenic chamber make me look fat?',
     'You are, what, like 15?',
      '4?',
     "Wait! Hang on. Are you going to be OK?"].include? said
  end

  def they_are_too_quiet? said
    ['', '    '].include? said
  end
end
