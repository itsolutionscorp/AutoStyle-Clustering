class Bob

  RESPONSE = {
    'Whatever.' => ['Tom-ay-to, tom-aaaah-to.',
                    "Let's go make out behind the gym!",
                    "It's OK if you don't want to go to the DMV.",
                    'Ending with ? means a question.'],

    'Woah, chill out!' => ['WATCH OUT!',
                          'WHAT THE HELL WERE YOU THINKING?',
                          '1, 2, 3 GO!',
                          'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!',
                          'I HATE YOU'],

    'Sure.' => ['Does this cryogenic chamber make me look fat?'],

    'Fine. Be that way!' => []
  }

  def hey(message)
    RESPONSE.each_key do |k|
      return k if RESPONSE[k].empty?
      return k if RESPONSE[k].include? message
    end
  end

end
