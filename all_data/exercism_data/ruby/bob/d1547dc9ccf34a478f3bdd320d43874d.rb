class Bob

  def initialize
    @response = 'Whatever.'
  end

  def hey(retort)
    parse_retort(retort)
    return @response
  end

  def parse_retort(retort)
    unless retort.nil?
      if blank?(retort)
        @response = 'Fine. Be that way!'
      elsif shouting?(retort) and
        @response = 'Woah, chill out!'
      elsif question?(retort)
        @response = 'Sure.'
      end
    end
  end

  def numeric_statement?(retort)
    only_numbers?(retort) and !numeric_question?(retort)
  end

  def multiline?(retort)
    !retort.scan("\n").empty?
  end

  def blank?(retort)
    !multiline?(retort) and retort.scan(/\S/).empty?
  end

  def shouting?(retort)
    !numeric_question?(retort) and !only_numbers?(retort) and  retort == retort.upcase
  end

  def question?(retort)
    retort =~ /\A.*\?\Z/
  end

  def only_numbers?(retort)
    retort.scan(/[a-zA-Z]/).empty?
  end

  def numeric_question?(retort)
    only_numbers?(retort) and question?(retort)
  end
end
