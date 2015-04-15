class Bob
  def initialize
    @responses = {
      blank: 'Fine. Be that way!',
      shouted: 'Woah, chill out!',
      question: 'Sure.',
      whatever: 'Whatever.'
    }
  end
  attr_accessor :responses
    
  def hey statement
    type = Statement.new(statement).type
    responses[type]
  end
end

class Statement
  def initialize statement
    @statement = statement
  end
  attr_accessor :statement

  def type
    if statement.blank?
      :blank
    elsif statement.shouting?
      :shouted
    elsif statement.question?
      :question
    else
      :whatever
    end
  end
end

class String
  def blank?
    self.nil? || self.strip.empty?
  end
  
  def last
    self[-1]
  end

  def shouting?
    self.upcase == self
  end

  def question?
    '?' == self.last
  end
end

class NilClass
  def blank?
    true
  end
end
