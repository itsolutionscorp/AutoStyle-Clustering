class BobResponse
  attr_reader :say

  def initialize(say, check)
    @check = check
    @say = say
  end

  def for?(greeting)
    return @check.call(greeting) if @check.respond_to? :call
    greeting =~ @check
  end
end


class Bob
  attr_reader :bob_responses

  def initialize
    shout_check = Proc.new do |greeting|
      if greeting.match /[a-z]/
        false
      else
        greeting.match /[A-Z]{2,}/
      end
    end

    @bob_responses = [
      BobResponse.new("Woah, chill out!", shout_check),
      BobResponse.new("Sure.", /\?\z/),
      BobResponse.new("Fine. Be that way!", /^\s*\z/),
      BobResponse.new("Whatever.", /.*/)
    ]
  end

  def hey(greeting)
    response =  bob_responses.find { |response| response.for? greeting }
    response.say
  end

end
