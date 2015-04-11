require 'ostruct'

class Bob
  def hey(expression)
    return response = case expression
    when patterns[:silence]
      'Fine. Be that way!'
    when patterns[:yelling]
      'Woah, chill out!'
    when patterns[:question]
      'Sure.'
    when patterns[:anything]
      'Whatever.'
    end
  end

private

  def patterns
    {
      silence: /\A\s*\z/,
      yelling: /\A[^a-z]*[A-Z][^a-z]*\z/,
      question: /\?\z/,
      anything: //,
    }
  end
end
