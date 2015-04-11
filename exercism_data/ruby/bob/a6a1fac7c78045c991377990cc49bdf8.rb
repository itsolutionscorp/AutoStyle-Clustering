class Bob
  attr_reader :answer_on

  def initialize
    @answer_on = {
      shout: 'Woah, chill out!',
      question: 'Sure.',
      saying_nothing: 'Fine. Be that way!',
      anything_else: 'Whatever.'
    }
  end

  def hey(saying)
    case saying
    when shout? then answer_on[:shout]
    when question? then answer_on[:question]
    when nothing? then answer_on[:saying_nothing]
    else
      answer_on[:anything_else]
    end
  end

  private

  def shout?
    ->(saying) do
      count_of_uppercase_letters = saying.scan(/[A-Z]/).count
      count_of_any_letters       = saying.scan(/[a-z]/i).count

      count_of_uppercase_letters == count_of_any_letters && count_of_any_letters > 0
    end
  end

  def question?
    ->(saying) do
      saying.end_with? '?'
    end
  end

  def nothing?
    ->(saying) { saying.strip.empty? }
  end

end
