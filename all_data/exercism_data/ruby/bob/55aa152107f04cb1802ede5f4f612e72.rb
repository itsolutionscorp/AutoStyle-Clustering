class Bob
  def hey(greeting)
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
    @greeting = greeting.to_s
  end

  def silent?
    greeting.strip.empty?
  end

  def shouting?
    greeting.upcase == greeting
  end

  def interrogating?
    greeting.end_with?("?")
  end
end
