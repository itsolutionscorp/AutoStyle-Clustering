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
      responses.fetch(response_type){ responses['default'] }
    end

    private

      def responses
        {
          'default'  => 'Whatever.',
          'shouting' => 'Woah, chill out!',
          'question' => 'Sure.',
          'forceful' => 'Whatever.',
          'silence'  => 'Fine. Be that way!'
        }
      end

      def response_type
        return 'silence'  if statement_was_silence?
        return 'shouting' if statement_was_shouting?
        return 'question' if statement_was_a_question?
        return 'default'
      end

      # tests if all letters are uppercase and
      # ignores special characters
      #
      def statement_was_shouting?
        @statement.match( /[A-Z]/ ) && !@statement.match( /[a-z]/ )
      end

      # tests if the statement is nil or
      # only contains space characters
      #
      def statement_was_silence?
        return true if @statement.nil? || @statement.empty?

        @statement.gsub( /\s/, '' ).empty?
      end

      def statement_was_a_question?
        last_char_of_statement == '?'
      end

      def last_char_of_statement
        @_last_char ||= @statement[-1]
      end

  end
end
