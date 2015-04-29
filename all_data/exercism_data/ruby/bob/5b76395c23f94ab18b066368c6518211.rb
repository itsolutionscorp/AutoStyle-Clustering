require 'delegate'
class Bob
  def hey(str)
    statement = Statement.new(str || "")
    return 'Fine. Be that way.' if statement.silence?
    return 'Sure.' if statement.question?
    return 'Woah, chill out!' if statement.yelling?
    'Whatever.'
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
      nil? || empty?
    end
  end
end
