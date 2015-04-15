class Bob

  def initialize
    @potential_responses = []
    @potential_responses << InappropriateResponse.new('Fine. Be that way.') { statement.silence? }
    @potential_responses << InappropriateResponse.new('Woah, chill out!') { statement.shouting? }
    @potential_responses << InappropriateResponse.new('Sure.') { statement.question? }
    @potential_responses << InappropriateResponse.new('Whatever.') { statement.anything? }
  end

  def hey(statement)
    @statement = Statement.new(statement)

    @potential_responses.find { |potential_response| potential_response.what_was_heard }.response
  end

  private

  attr_reader :statement
end

class InappropriateResponse
  attr_reader :response, :test_for_what_was_heard

  def initialize(response, &test_for_what_was_heard)
    @response = response
    @test_for_what_was_heard = test_for_what_was_heard
  end

  def what_was_heard
    @test_for_what_was_heard.yield
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
