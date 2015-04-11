require 'forwardable'
class Bob
  def hey(str)
    Decider.new(str).decision
  end

  class Decider
    extend Forwardable

    attr_accessor :subject

    def_delegators :subject, :empty?
    def_delegators "self.class", :decisions

    def initialize(subject)
      self.subject = subject.to_s
    end


    def decision
      if empty?
        "Fine. Be that way!"
      elsif all_caps?
        "Woah, chill out!"
      elsif question?
        "Sure."
      else
        "Whatever."
      end
    end

    def all_caps?
      subject.upcase == subject
    end

    def question?
      subject.match /\?\Z/
    end
  end
end
