class Bob
  def hey(given)
    statement = Statement.new(given)
    statement.get_response
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
    @statement[-1] == '?'
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
      if self.send("#{type}?")
        return response
      end
    end
  end
end
