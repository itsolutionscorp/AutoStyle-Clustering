class Bob

  def hey statement
    Respond.to( statement )
  end

  class Respond

    class << self
      def to statement
        Respond.new(statement).respond
      end
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
        return response_type_by_last_character
      end

      # tests if all letters are uppercase and
      # ignores special characters
      #
      def statement_was_shouting?
        @statement.gsub( /[^a-zA-Z]/, '' ).gsub( /[A-Z]/, '' ).empty?
      end

      # tests if the statement is nil or
      # only contains space characters
      #
      def statement_was_silence?
        return true if @statement.nil? || @statement.empty?

        @statement.gsub( /\s/, '' ).empty?
      end

      def response_type_by_last_character
        case last_char_of_statement
        when '?' then 'question'
        when '!' then 'forceful'
        else
          'default'
        end
      end

      def last_char_of_statement
        @_last_char ||= @statement[@statement.length-1]
      end

  end
end
