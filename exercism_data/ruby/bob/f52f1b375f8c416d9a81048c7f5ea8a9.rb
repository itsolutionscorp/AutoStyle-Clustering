class Bob
  InappropriateResponse = Struct.new(:response, :what_was_heard)

  attr_reader :statement

  def initialize
    @potential_responses = []
    @potential_responses << InappropriateResponse.new('Fine. Be that way.', ->{ self.statement.silence? })
    @potential_responses << InappropriateResponse.new('Woah, chill out!', ->{ self.statement.shouting? })
    @potential_responses << InappropriateResponse.new('Sure.', ->{ self.statement.question? })
    @potential_responses << InappropriateResponse.new('Whatever.', ->{ self.statement.anything? })
  end

  def hey(statement)
    @statement = Statement.new(statement)

    @potential_responses.find { |potential_response| potential_response.what_was_heard.call }.response
  end
end

class Statement

  def initialize(statement)
    @statement = statement || ''
  end

  def silence?
    statement.empty?
  end

  def shouting?
    statement == statement.upcase
  end

  def question?
    statement.end_with?('?')
  end

  def anything?
    true
  end

  private

  attr_reader :statement
end
