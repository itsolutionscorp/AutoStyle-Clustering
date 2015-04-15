require 'active_support/all'

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
      if retort.blank?
        @response = 'Fine. Be that way!'
      elsif shouting?(retort)
        @response = 'Woah, chill out!'
      elsif question?(retort)
        @response = 'Sure.'
      end
    end
  end

  def shouting?(retort)
    !numeric_question?(retort) and !only_numbers?(retort) and  retort == retort.upcase
  end

  def question?(retort)
    retort.ends_with?('?')
  end

  def only_numbers?(retort)
    retort.scan(/[a-zA-Z]/).empty?
  end

  def numeric_question?(retort)
    only_numbers?(retort) and question?(retort)
  end
end
