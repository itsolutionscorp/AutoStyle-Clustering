# require "behaviorist/*.rb"
module Behaviorist class Subject ; end ; end

# bob.rb
class Bob < Behaviorist::Subject
  def initialize
    super do
      respond_to :silence?, with: "Fine. Be that way!"
      respond_to :yell?, with: "Woah, chill out!"
      respond_to :question?, with: "Sure."
      respond_to anything_else, with: "Whatever."
    end
  end

  def hey statement
    converse statement
  end
end

# behaviorist/*.rb
module Behaviorist
  class Subject
    def initialize &block
      @dispositions = []
      SkinnerDSL.new dispositions, &block
      @dispositions << Disposition::TO_DO_NOTHING
    end

    def behave stimulus
      disposition_by(stimulus).response
    end

    def converse statement
      behave( VerbalStimulus.new statement )
    end

    private

    attr_reader :dispositions

    def disposition_by stimulus
      dispositions.find do |disposition|
        disposition.activated_by? stimulus
      end
    end

    class SkinnerDSL
      attr_reader :dispositions

      def initialize dispositions, &block
        @dispositions = dispositions
        instance_eval &block
      end

      def respond_to condition, with:
        condition &&= condition.to_proc
        response = with
        dispositions << Disposition.new(response, &condition)
      end

      def anything_else
        nil
      end
    end
  end

  class Disposition
    attr_reader :response, :condition
    UNCONDITIONALLY = ->(ignored_stimulus) { true }
    TO_DO_NOTHING = Disposition.new

    def initialize response = nil, &condition
      @response = response
      @condition = condition || UNCONDITIONALLY
    end

    def activated_by? stimulus
      condition[ stimulus ]
    end
  end

  class VerbalStimulus < String
    def silence?
      strip.empty?
    end

    def yell?
      match /\p{Upper}/ and not match /\p{Lower}/
    end

    def question?
      end_with? "?"
    end
  end
end
