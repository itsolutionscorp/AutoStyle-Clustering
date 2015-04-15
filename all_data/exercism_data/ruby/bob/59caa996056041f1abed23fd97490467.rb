class Bob

  def hey(str)
    string = StringInterpreter.new(str)

    # yes, obviously this sucks. help?
    if string.is_empty_or_nil?
      TeenageResponder.fine
    elsif string.is_upcased?
      TeenageResponder.chill_out
    elsif string.ends_in_question_mark?
      TeenageResponder.sure
    else
      TeenageResponder.whatever
    end
  end

end

class StringInterpreter

  attr_reader :string

  def initialize(string)
    @string = string
  end

  def is_empty_or_nil?
    string.nil? || string.empty?
  end

  def is_upcased?
    string.upcase == string
  end

  def ends_in_question_mark?
    string =~ /\?$/
  end

end

class TeenageResponder

  class << self

    def fine
      return 'Fine. Be that way.'
    end

    def chill_out
      'Woah, chill out!'
    end

    def sure
      'Sure.'
    end

    def whatever
      'Whatever.'
    end

  end

end
