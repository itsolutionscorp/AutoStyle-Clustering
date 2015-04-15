class Bob
  def hey(str)
    ask = Ask.new(str)

    if ask.nothing?
      'Fine. Be that way!'
    elsif ask.yell?
      'Woah, chill out!'
    elsif ask.question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  class Ask
    def initialize(str)
      str ||= ''
      @ask = str.strip
    end

    def nothing?
      @ask.empty?
    end

    def yell?
      !nothing? && @ask.upcase == @ask
    end

    def question?
      !nothing? && !yell? && @ask.end_with?('?')
    end
  end
end
