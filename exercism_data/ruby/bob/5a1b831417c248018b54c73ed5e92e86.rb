class Bob
  attr_reader :msgs, :default_msg

  def initialize
    @default_msg = 'Fine. Be that way.'
    @msgs = {
      'Tom-ay-to, tom-aaaah-to.' => 'Whatever.',
      "Let's go make out behind the gym!" => 'Whatever.',
      'Ending with ? means a question.' => 'Whatever.',
      'WATCH OUT!' => 'Woah, chill out!',
      '1, 2, 3 GO!' => 'Woah, chill out!',
      'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!' => 'Woah, chill out!',
      'I HATE YOU' => 'Woah, chill out!',
      'Does this cryogenic chamber make me look fat?' => 'Sure.',
    }
  end

  def hey(msg)
    @msgs[msg] || @default_msg
  end
end
