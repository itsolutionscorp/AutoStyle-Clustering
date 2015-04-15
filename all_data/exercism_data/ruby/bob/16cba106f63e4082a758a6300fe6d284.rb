class Bob
  def hey(statement)
    BobsResponse.new(statement).response
  end
end

class BobsResponse

  attr_reader :response

  def initialize(statement)
    @statement = statement
    @response = build_response(statement)
  end

  def build_response(statement)
    if @statement.strip.empty?
      'Fine. Be that way!'
    elsif @statement.upcase == statement && (statement =~ /[A-Z]/)
      'Woah, chill out!'
    elsif @statement.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end
end
