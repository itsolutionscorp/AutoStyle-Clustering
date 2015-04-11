class Bob

  QUESTION = /\?\z/
  YELL     = /\A(?=.*[A-Z].*)[^a-z]+\z/
  SILENCE  =  /\A\s*\z/
  DEFAULT  = //

  RESPONSES = {
    YELL     => 'Woah, chill out!',
    QUESTION => 'Sure.',
    SILENCE  => 'Fine. Be that way!',
    DEFAULT  => 'Whatever.'
  }

  def hey phrase
    RESPONSES.find { |regexp, answer| phrase =~ regexp }.last
  end

end
