require 'yaml'
class Bob

  RULES = YAML.load <<-YML
    Whatever.:
      - Tom-ay-to, tom-aaaah-to.
      - Let's go make out behind the gym!
      - It's OK if you don't want to go to the DMV.
      - Ending with ? means a question.
    Woah, chill out!:
      - WATCH OUT!
      - WHAT THE HELL WERE YOU THINKING?
      - 1, 2, 3 GO!
      - ZOMG THE %^*@#\$(*^ ZOMBIES ARE COMING!!11!!1!
      - I HATE YOU
    Sure.:
      - Does this cryogenic chamber make me look fat?
      - Wait! Hang on. Are you going to be OK?
    Fine. Be that way!:
      - ''
      -
      - '    '
  YML

  RESPONSES = RULES.reduce({}) do |hash, (response, phrases)|
    phrases.each {|phrase| hash.update phrase => response }
    hash
  end

  def hey(incoming)
    RESPONSES[incoming]
  end
end
