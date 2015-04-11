require 'delegate'
class Bob
  def hey(str)
    statement = Statement.new(str || "")
    case
    when statement.silence?
      'Fine. Be that way.'
    when statement.question?
      'Sure.'
    when statement.yelling?
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end

  private
  class Statement < String
    def yelling?
      upcase == self
    end

    def question?
      end_with? '?'
    end

    def silence?
      empty?
    end
  end
end
