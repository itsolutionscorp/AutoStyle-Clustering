class Bob
  def hey(given)
    Statement.new(given).get_response
  end
end

class Statement
  RESPONSES = {
    yelling:  'Woah, chill out!',
    question: 'Sure.',
    silence:  'Fine. Be that way!',
    other:    'Whatever.'
  }

  def initialize(statement)
    @statement = statement
  end

  def question?
    @statement.end_with? '?'
  end

  def yelling?
    @statement == @statement.upcase && @statement != @statement.downcase
  end

  def silence?
    @statement.strip.empty?
  end

  def other?
    true
  end

  def get_response
    RESPONSES.each do |type, response|
      return response if self.send("#{type}?")
    end
  end
end
