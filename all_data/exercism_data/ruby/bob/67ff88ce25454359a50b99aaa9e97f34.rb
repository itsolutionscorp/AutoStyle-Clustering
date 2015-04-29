# encoding: utf-8

class Bob
  def hey(string)
    case string
    when InputDetector::Silence
      then 'Fine. Be that way!'
    when InputDetector::Yelling
      then 'Woah, chill out!'
    when InputDetector::Question
      then 'Sure.'
    else
      'Whatever.'
    end
  end
end

module InputDetector
  module Yelling
    extend self
    def === (input)
      input.upcase == input
    end
  end

  module Question
    extend self
    def === (input)
      input.end_with? ??
    end
  end

  module Silence
    extend self
    def === (input)
      input.nil? or input.empty?
    end
  end
end
