class Bob
  def hey(message)
    case message
    when silent   then 'Fine. Be that way!'
    when shout    then 'Woah, chill out!'
    when question then 'Sure.'
    else               'Whatever.'
    end
  end

  private

  def silent
    ->(message) { message.nil? || message.strip == '' }
  end

  def shout
    ->(message) { message.upcase == message }
  end

  def question
    ->(message) { message.end_with?('?') }
  end
end
