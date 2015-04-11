class Bob
  def hey(greeting)
    return 'Fine. Be that way!' if Greeting.new(greeting).silence?
    return 'Woah, chill out!' if Greeting.new(greeting).shouting?
    return 'Sure.' if Greeting.new(greeting).question?
    'Whatever.'
  end
end

class Greeting
  def initialize(greeting)
    @greeting = greeting
  end

  def silence?
    @greeting.strip.empty?
  end

  def shouting?
    @greeting.match(/\A[^a-z]*\Z/) && @greeting.match(/[A-Z]/)
  end

  def question?
    @greeting.match(/\?\Z/)
  end
end
