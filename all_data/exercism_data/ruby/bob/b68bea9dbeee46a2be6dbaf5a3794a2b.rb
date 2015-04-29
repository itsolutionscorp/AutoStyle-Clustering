class Bob
    InappropriateResponse = Struct.new(:response, :what_was_heard)

  def initialize
    @potential_responses = []
    @potential_responses << InappropriateResponse.new('Fine. Be that way.', method(:silence?))
    @potential_responses << InappropriateResponse.new('Woah, chill out!', method(:shouting?))
    @potential_responses << InappropriateResponse.new('Sure.', method(:question?))
    @potential_responses << InappropriateResponse.new('Whatever.', method(:anything?))
  end

  def hey(statement)
    respond(statement || '')
  end

  def silence?(statement)
     statement.empty?
  end

  def shouting?(statement)
     statement == statement.upcase && !silence?(statement)
  end

  def question?(statement)
     statement.end_with?('?')
  end

  def anything?(_)
    true
  end

  def respond(statement)
    @potential_responses.find {|potential_response| potential_response.what_was_heard[statement]}.response
  end
end
