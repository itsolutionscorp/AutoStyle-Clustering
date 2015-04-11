class Bob
  def hey(message)
    message ||= ''

    case message
    when SILENT
      'Fine. Be that way!'
    when SHOUTING
      'Woah, chill out!'
    when QUESTION
      'Sure.'
    else
      'Whatever.'
    end
  end

  private
  SILENT = ->(message) { message.strip.empty? }
  SHOUTING = ->(message) { message.upcase == message }
  QUESTION = ->(message) { message.end_with?('?') }
end
