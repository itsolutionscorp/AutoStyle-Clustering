class Bob
  def hey(incoming_remark)
    LinguisticCircuit.new(incoming_remark).reply
  end

  class LinguisticCircuit
    attr_reader :remark

    def initialize(remark)
      @remark = remark
    end

    def reply
      if feeling_ignored
        "Fine. Be that way!"
      elsif experiencing_aggression
        "Woah, chill out!"
      elsif faced_with_a_question
        "Sure."
      else
        "Whatever."
      end
    end

    def detecting_ostracism
      remark.strip.empty?
    end

    def experiencing_aggression
      remark == remark.upcase
    end

    def faced_with_a_question
      remark.end_with? '?'
    end
  end
end
