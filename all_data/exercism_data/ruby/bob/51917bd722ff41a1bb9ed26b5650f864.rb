class Bob
  attr_reader :is_shouting, :is_a_question, :is_nothing

  def initialize
    @is_shouting = ->(str) { str == str.upcase && str =~ /[[:alpha:]]/ }
    @is_a_question = ->(str) { str.end_with?('?') }
    @is_nothing = ->(str) { !(str =~ /\S/) }
  end

  def hey(input)
    case input
    when is_shouting then 'Woah, chill out!'
    when is_a_question then 'Sure.'
    when is_nothing then 'Fine. Be that way!'
    else 'Whatever.'
    end
  end
end
