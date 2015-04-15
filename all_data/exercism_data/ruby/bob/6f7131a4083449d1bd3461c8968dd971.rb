module Translator
  TeenagerTalk = Struct.new(:string) do
    def translate
      case
      when self.not_saying_anything?  then "Fine. Be that way!"
      when self.yelling?              then "Woah, chill out!"
      when self.a_question?           then "Sure."
      when self.something_else?       then "Whatever."
      else raise "The translator module has failed to translate. Oh noes!!!"
      end
    end

    def not_saying_anything?
      (string.nil? || string.strip.empty?)
    end

    def yelling?
      string.upcase == string
    end

    def a_question?
      string.end_with? "?"
    end

    def something_else?
      true
    end
  end
end

class Bob
  include ::Translator

  def hey(input)
    TeenagerTalk.new(input).translate
  end
end
