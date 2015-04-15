require 'rubygems'
require 'pry'

class Bob
  def tests
    {:silence => proc { |x| x.strip.length == 0 }, 
     :shout => proc { |x| x.strip.upcase == x and x =~ /[A-Z]/},
     :question => proc { |x| x.end_with? "?" }, 
     :whatever => proc { true }
    }
  end

  def question_type(question)
    tests.find { |k,prc| prc.call(question)  }.first
  end

  def answer(question_type)
    case question_type
    when :question
      'Sure.'
    when :shout
      'Woah, chill out!'
    when :silence
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
  def hey(question)
    qt = question_type(question)
    answer(qt)
  end
end
