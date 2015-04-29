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

    def self.decision(bool_method = :default, &block)
      decisions[bool_method] = block
    end

    def self.decisions
      @decisions ||= {} # we want order
    end

    def initialize(subject)
      self.subject = subject.to_s
    end

    decision(:empty?) { "Fine. Be that way!" }
    decision(:all_caps?) { "Woah, chill out!" }
    decision(:question?) { "Sure." }
    decision { "Whatever." }

    def decision
      decision_name = decisions.keys.find { |method| send(method) if respond_to?(method) } || :default
      decisions[decision_name].call
    end

    def all_caps?
      subject.upcase == subject
    end

    def question?
      subject.match /\?\Z/
    end
  end
end
