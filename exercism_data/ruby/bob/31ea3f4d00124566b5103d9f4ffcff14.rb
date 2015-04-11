class Bob

  WHATEVER = 'Whatever.'
  WHOA = 'Woah, chill out!'
  SURE = 'Sure.'
  FINE = 'Fine. Be that way!'

  def initialize
    @mapping = {
      %Q(Tom-ay-to, tom-aaaah-to.) => WHATEVER,
      %Q(WATCH OUT!) => WHOA,
      %Q(Does this cryogenic chamber make me look fat?) => SURE,
      %Q(You are, what, like 15?) => SURE,
      %Q(Let's go make out behind the gym!) => WHATEVER,
      %Q(It's OK if you don't want to go to the DMV.) => WHATEVER,
      %Q(WHAT THE HELL WERE YOU THINKING?) => WHOA,
      %Q(1, 2, 3 GO!) => WHOA,
      'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!' => WHOA,
      %Q(I HATE YOU) => WHOA,
      %Q(Ending with ? means a question.) => WHATEVER,
      %Q(Wait! Hang on. Are you going to be OK?) => SURE,
      %Q(Does this cryogenic chamber make me look fat? no) => WHATEVER,
      '' => FINE
    }
  end

  def hey(string)
    string.gsub!("\n", ' ')
    string.strip!
    @mapping[string]
  end
end
