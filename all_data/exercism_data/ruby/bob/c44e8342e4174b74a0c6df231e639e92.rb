class Bob
  TeenagerTalk = Struct.new(:string) do
    def not_saying_anything?; string.nil? || string.strip.empty?; end
    def yelling?;             string.upcase == string;            end
    def a_question?;          string.end_with? "?";               end
  end

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
