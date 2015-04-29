class Bob
  def hey(statement)
    person = Person.new statement

    case 
      when person.not_talking? then "Fine. Be that way."
      when person.shouting? then "Woah, chill out!"
      when person.asking? then "Sure."
      else "Whatever."
    end
  end
end

class Person
  def initialize(statement)
    @statement = statement
  end

  def not_talking?
    @statement.to_s.empty?
  end

  def shouting?
    @statement == @statement.upcase
  end

  def asking?
    @statement.end_with?("?")
  end
end
