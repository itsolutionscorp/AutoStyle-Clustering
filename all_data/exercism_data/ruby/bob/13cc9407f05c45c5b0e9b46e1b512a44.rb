require 'forwardable'
class Bob
  def hey(str)
    BobResponder.new(str).respond
  end

  class BobResponder
    extend Forwardable

    attr_accessor :subject

    def_delegators :subject, :empty?

    def initialize(subject)
      self.subject = subject.to_s
    end


    def respond
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
