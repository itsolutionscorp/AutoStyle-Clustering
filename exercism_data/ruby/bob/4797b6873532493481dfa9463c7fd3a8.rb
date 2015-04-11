class Bob
  def hey(greeting=nil)
    @person = Person.new(greeting:greeting)

    if @person.silent?
      "Fine. Be that way!"
    elsif @person.shouting?
      "Woah, chill out!"
    elsif @person.interrogating?
      "Sure."
    else
      "Whatever."
    end
  end
end

class Person
  attr_accessor :greeting

  def initialize(options={})
    @greeting = options[:greeting]
  end

  def silent?
    greeting.to_s.strip.empty?
  end

  def shouting?
    greeting.upcase == greeting
  end

  def interrogating?
    greeting.end_with?("?")
  end
end
