class Bob
  def hey(message)
    {
      silence => 'Fine. Be that way!',
      shouting => 'Woah, chill out!',
      question => 'Sure.',
      everything_else => 'Whatever.'
    }.find { |matcher, _| matcher[message] }.last
  end

  private
  def silence
    ->(message) { message.strip.empty? }
  end

  def shouting
    ->(message) {
      letters = message.gsub(/[^A-Za-z]/, '').chars
      letters.all? { |chr| ('A'..'Z').include?(chr) } && !letters.empty?
    }
  end

  def question
    ->(message) { message[-1] == '?' }
  end

  def everything_else
    ->(message) { true }
  end
end
