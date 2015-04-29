# encoding: UTF-8

class Bob
  def hey(said)
    they_need_to_chill = [
        'WATCH OUT!',
        'WHAT THE HELL WERE YOU THINKING?',
        '1, 2, 3 GO!',
        'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!',
        'I HATE YOU']

    sures = ['Does this cryogenic chamber make me look fat?',
             'You are, what, like 15?',
             '4?',
             "Wait! Hang on. Are you going to be OK?"]

    they_are_too_quiet = ['',
                          '    ']

    if they_are_too_quiet.include?(said)
      'Fine. Be that way!'
    elsif they_need_to_chill.include?(said)
      'Woah, chill out!'
    elsif sures.include?(said)
      'Sure.'
    else
      'Whatever.'
    end
  end
end
