class Bob
  RESPONSES = {
    silence: 'Fine. Be that way!',
    aggressive: 'Woah, chill out!',
    interrogative: "Sure."
  }
  RESPONSES.default = "Whatever."

  def hey(arg)
    response_for IncomingStatement.new(arg).type
  end

  private

    def response_for(statement_type)
      RESPONSES[statement_type]
    end

  class IncomingStatement < Struct.new(:statement)
    INTERACTION_TYPES = %i[silence aggressive interrogative default]
    def type
      INTERACTION_TYPES.find { |k| send :"#{k}?"} 
    end

    private
      def silence?
        statement.to_s == ''
      end

      def interrogative?
        statement =~ /\?$/
      end

      def aggressive?
        statement.upcase == statement
      end

      def default?
        true
      end
  end

end
