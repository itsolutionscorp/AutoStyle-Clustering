class Bob
  def hey what_they_said
    annoying_person = Person.new what_they_said

    if annoying_person.said_nothing?
      respond 'Fine. Be that way!'
    elsif annoying_person.is_yelling? 
      respond 'Woah, chill out!'
    elsif annoying_person.is_asking? 
      respond 'Sure.'
    else 
      respond 'Whatever.'
    end
  end

  private
  def respond words
    return words
  end
end

class Person
  def initialize statement
    @statement = statement.to_s
  end

  def said_nothing?
    @statement.to_s.empty?
  end

  def is_yelling?
    @statement.upcase == @statement
  end

  def is_asking?
    @statement.end_with? '?'
  end
end
