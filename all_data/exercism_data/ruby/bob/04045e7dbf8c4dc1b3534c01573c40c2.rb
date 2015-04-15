# encoding: utf-8

class Bob
  def hey(string)
    case string
    when InputDetector::Silence
      'Fine. Be that way!'
    when InputDetector::Yelling
      'Woah, chill out!'
    when InputDetector::Question
      'Sure.'
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
