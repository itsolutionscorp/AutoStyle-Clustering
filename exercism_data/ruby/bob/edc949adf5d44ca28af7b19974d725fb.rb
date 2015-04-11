class Reaction
  def initialize(response, &block)
    @test = block
    @response = response
  end

  def response_for(input)
    @response if @test.call(input)
  end
end

class Bob
  silence_reaction = Reaction.new('Fine. Be that way!') do |input|
    input.strip == ''
  end
  shouting_reaction = Reaction.new('Woah, chill out!') do |input|
    input.upcase == input
  end
  question_reaction = Reaction.new('Sure.') do |input|
    input.end_with? '?'
  end
  default_reaction = Reaction.new('Whatever.') do |input|
    true
  end

  REACTIONS = [
    silence_reaction,
    shouting_reaction,
    question_reaction,
    default_reaction
  ]

  def hey(input)
    input ||= ''
    REACTIONS.each do |reaction|
      response = reaction.response_for(input)
      break response if response
    end
  end
end
