class Bob
  include Translator

  def hey(input)
    string = TeenagerTalk.new input

    case
    when string.not_saying_anything? then 'Fine. Be that way!'
    when string.yelling?             then 'Woah, chill out!'
    when string.a_question?          then 'Sure.'
    else                                  'Whatever.'
    end
  end
end

module Translator
  TeenagerTalk = Struct.new(:string) do
    def not_saying_anything?
      return true if string.nil?
      string.each_char { |character| return false unless character == " " }
      true
    end

    def yelling?
      string.upcase == string
    end

    def a_question?
      string.end_with? "?"
    end
  end
end
