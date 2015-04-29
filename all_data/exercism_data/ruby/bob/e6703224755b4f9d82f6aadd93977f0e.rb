############################################
#
# Class Response
#

class Response
  attr_accessor :input

  SENTENCE_SUFFIXES = ['.', '!']

  def initialize(input)
    @input = input
  end

  def respond
    generate_response
  end

  private

    def generate_response
      if is_sentence?
        'Whatever.'
      elsif is_yelling?
        'Woah, chill out!'
      elsif is_question?
        'Sure.'
      elsif is_silence?
        'Fine. Be that way.'
      end
    end

    def is_sentence?
      input.end_with? *SENTENCE_SUFFIXES unless is_silence? || is_yelling?
    end

    def is_yelling?
      input == input.upcase unless is_silence?
    end

    def is_question?
      input.end_with? '?' unless is_silence?
    end

    def is_silence?
      is_blank?
    end

    def is_blank?
      input.nil? || is_empty_string?
    end

    def is_empty_string?
      input == ''
    end
end


############################################
#
# Class Bob
#

class Bob
  def hey(input)
    respond(input)
  end

  private

    def respond(input)
      Response.new(input).respond
    end
end
