class Bob

  def hey message
    "#{message}".strip.tap {|e| e.extend Behavior}.respond
  end

  module Behavior
    def shouting?
      self !~ /[a-z]/
    end

    def question?
      self.end_with? '?'
    end

    def respond
      if self.empty?
        'Fine. Be that way!'
      elsif self.shouting?
        'Woah, chill out!'
      elsif self.question?
        'Sure.'
      else
        'Whatever.'
      end
    end
  end
end
