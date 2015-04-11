class Bob
  def hey(greeting=nil)
    person = Person.new(greeting)

    if person.silent?
      "Fine. Be that way!"
    elsif person.shouting?
      "Woah, chill out!"
    elsif person.interrogating?
      "Sure."
    else
      "Whatever."
    end
  end
end

class Person
  attr_accessor :greeting

  def initialize(greeting)
    @greeting = greeting
  end

  def silent?
    greeting.to_s.strip.empty?
  end

  def shouting?
    greeting.to_s.upcase == greeting
  end

  def interrogating?
    greeting.to_s.end_with?("?")
  end
end
