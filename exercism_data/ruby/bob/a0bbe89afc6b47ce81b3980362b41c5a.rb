require 'pp'
class Bob
  class << self
    attr_reader :answers
    def add_answer(answer, test)
      @answers ||= []
      @answers << {answer: answer, test: test }
    end
  end

  add_answer 'Whoa, chill out!', ->(s){ s.upcase == s && s.match(/[a-zA-Z]/)}
  add_answer 'Sure.',->(s){ s.chars.last == '?' }
  add_answer 'Fine. Be that way!', ->(s){ s.strip == ''}
  add_answer 'Whatever.', -> (s) { true }

  def hey(remark)
    self.class.answers.find {|v| v[:test].call(remark) }[:answer]
  end

end
