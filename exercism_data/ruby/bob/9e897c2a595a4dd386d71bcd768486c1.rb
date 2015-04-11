require_relative 'message.rb'

class Bob
  def hey(something)
  	message = Message.new(something)

    return 'Fine. Be that way!' if message.blank?
    return 'Woah, chill out!' if message.shouting?
    return 'Sure.' if message.question?

    'Whatever.'
  end
end
