class Bob
  attr_accessor :message

  def hey(str)
    return 'Fine. Be that way!' if str.strip.empty?
    @message = str.strip
    parse_message
  end

  def parse_message
    message =~ /\?\z/ ? respond_to_question : respond_to_statement
  end

  def respond_to_question
    return 'Sure.' if message[/[a-z]/] || message[/[a-zA-Z]/].nil?
    'Woah, chill out!'
  end

  def respond_to_statement
    return 'Whatever.' if message[/[a-z]/] || message[/[a-zA-Z]/].nil?
    'Woah, chill out!'
  end

end
