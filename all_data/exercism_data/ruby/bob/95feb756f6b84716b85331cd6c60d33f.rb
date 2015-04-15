class Bob

  def initialize
    @responses_to = []
    @responses_to << method(:silence?)
    @responses_to << method(:shouting?)
    @responses_to << method(:question?)
    @responses_to << method(:anything?)
  end

  def hey(statement)
    @responses_to.reduce(nil) {|response, filter| response ||= filter.call(statement); response}
  end

  def silence?(statement)
    'Fine. Be that way.' if statement == '' || statement == nil
  end

  def shouting?(statement)
    'Woah, chill out!' if  statement == statement.upcase && !silence?(statement)
  end

  def question?(statement)
    'Sure.' if statement[-1] == '?'
  end

  def anything?(_)
    'Whatever.'
  end
end
