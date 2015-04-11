require 'pry'
class Bob
  # I considered storing @input as a class variable to clean up the
  # look of the instance methods but decided to just leave it out
  # in the open with the when-lambda construction.
  #
  def hey(input)
    case input.to_s
    when ->(i) { letters(i).none? }
      'Fine. Be that way.'
    when ->(i) { shouty?(i)       }
      'Woah, chill out!'
    when ->(i) { question?(i)     }
      'Sure.'
    else
      'Whatever.'
    end
  end

  private
  def letters(str)
    str.chars.select { |c| c =~ /[A-Za-z]/ }
  end

  def lowercase_letters(str)
    str.chars.select { |c| c =~ /[a-z]/ }
  end

  def shouty?(str)
    letters(str).any? && lowercase_letters(str).none?
  end

  def question?(str)
    str.end_with? '?'
  end
end
