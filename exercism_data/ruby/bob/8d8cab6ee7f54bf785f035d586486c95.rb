class Bob
  def hey(what)
    Conversation.new(what).response
  end

  class Conversation
    def initialize(string)
      @string = string
    end

    def response
      case
      when blank?    then 'Fine. Be that way!'
      when shouting? then 'Woah, chill out!'
      when question? then 'Sure.'
      else 'Whatever.'
      end
    end

    private

    def blank?
      @string.nil? or @string.empty?
    end

    def shouting?
      @string == @string.upcase
    end

    def question?
      @string[-1] == '?'
    end
  end

end
