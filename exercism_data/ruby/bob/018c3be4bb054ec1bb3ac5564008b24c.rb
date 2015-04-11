class Bob
  class Reaction < Struct.new(:test, :response)
    def response_for(input)
      response if test.call(input)
    end
  end

  REACTIONS = [
    [lambda {|input| input.strip == '' }, 'Fine. Be that way!'],
    [lambda {|input| input.match /^[^a-z]+$/ }, 'Woah, chill out!'],
    [lambda {|input| input.match /\?$/ }, 'Sure.'],
    [lambda {|input| true }, 'Whatever.']
  ].map {|arr| Reaction.new(*arr) }

  def hey(input)
    input ||= ''
    REACTIONS.each do |reaction|
      response = reaction.response_for(input)
      break response if response
    end
  end
end
