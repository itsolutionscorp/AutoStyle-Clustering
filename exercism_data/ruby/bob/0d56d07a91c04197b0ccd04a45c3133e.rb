# encoding: utf-8

class Bob
  def hey(greeting)
    case greeting
    when Remark::Silence
      'Fine. Be that way!'
    when Remark::Yelling
      'Woah, chill out!'
    when Remark::Question
      'Sure.'
    else
      'Whatever.'
    end
  end
end

module Remark
  Yelling   = ->(input) { input.upcase == input }
  Question  = ->(input) { input.end_with?(??) }
  Silence   = ->(input) { input.to_s.empty? }
end
