class Bob
  def hey(greeting)
    respond_to(Greeting.new(greeting))
  end

  private

  def respond_to(greeting)
    return 'Fine. Be that way!' if greeting.silence?
    return 'Woah, chill out!' if greeting.shouting?
    return 'Sure.' if greeting.question?
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
    has_no_lowercase_letters? && has_any_uppercase_letters?
  end

  def question?
    @greeting.end_with?('?')
  end

  private

  def has_any_uppercase_letters?
    @greeting.match(/[A-Z]/)
  end

  def has_no_lowercase_letters?
    @greeting.match(/\A[^a-z]*\Z/)
  end
end
