class Bob
  attr_reader :msgs, :default_msg

  def initialize
    @msgs = Hash.new { |hash, key| hash[key] = 'Fine. Be that way.' }

    @whatever = ['Tom-ay-to, tom-aaaah-to.',
                 "Let's go make out behind the gym!",
                 'Ending with ? means a question.']

    @woah = ['WATCH OUT!', 
             '1, 2, 3 GO!',
             'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!',
             'I HATE YOU']

    @sure = ['Does this cryogenic chamber make me look fat?']

    @whatever.each { |t| @msgs[t] = 'Whatever.' }
    @woah.each     { |t| @msgs[t] = 'Woah, chill out!' }
    @sure.each     { |t| @msgs[t] = 'Sure.' }
  end

  def hey(msg)
    @msgs[msg]
  end
end
