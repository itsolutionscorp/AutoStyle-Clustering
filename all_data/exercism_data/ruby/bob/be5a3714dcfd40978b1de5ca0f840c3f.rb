class Bob
  attr_reader :message

  def hey(message)
    @message = message
    if message.match(/[a-zA-Z]/)
      shouting = message.split.none? do |letter|
        letter == letter.downcase && letter != letter.upcase
      end
    end
    process_tone_of_voice(shouting)
  end

  private

  def process_tone_of_voice(shouting=false)
    if shouting
      'Woah, chill out!'
    elsif
      message[-1] == '?'
      'Sure.'
    elsif
      !message.match(/[a-zA-Z0-9]/)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
