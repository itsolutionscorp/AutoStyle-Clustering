require 'delegate'
class Bob
  def hey(str)
    statement = Statement.new(str)
    return 'Fine. Be that way.' if statement.silence?
    return 'Sure.' if statement.question?
    return 'Woah, chill out!' if statement.yelling?
    'Whatever.'
  end

  private
  class Statement < SimpleDelegator
    def yelling?
      self.upcase == self
    end

    def question?
      end_with? '?'
    end

    def silence?
      __getobj__.nil? || empty?
    end
  end
end
