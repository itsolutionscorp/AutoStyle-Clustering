class Bob

  def hey statement
    Respond.to( statement )
  end

  class Respond

    def self.to statement
      Respond.new(statement).respond
    end

    def initialize statement
      @statement = statement
    end

    def respond
      responses[response_type]
    end

    private

      def responses
        {
          'default'  => 'Whatever.',
          'shouting' => 'Woah, chill out!',
          'question' => 'Sure.',
          'silence'  => 'Fine. Be that way!'
        }
      end

      def response_type
        return 'silence'  if statement_was_silence?
        return 'shouting' if statement_was_shouting?
        return 'question' if statement_was_a_question?
        return 'default'
      end

      def statement_was_shouting?
        @statement.upcase == @statement
      end

      def statement_was_silence?
        @statement.nil? || @statement.strip.empty?
      end

      def statement_was_a_question?
        @statement.end_with? '?'
      end

  end
end
