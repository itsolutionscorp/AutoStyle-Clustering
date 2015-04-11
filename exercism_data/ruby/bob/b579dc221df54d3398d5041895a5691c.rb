BOBS_RESPONSES = Hash[:blow_off => 'Fine. Be that way!',
                      :shout => 'Woah, chill out!',
                      :question => 'Sure.',
                      :non_sequitir => 'Whatever.']

class Bob
  def hey(statement)
    BOBS_RESPONSES[Query.new(statement).statement_type]
  end
end


class Query

  attr_reader :statement_type

  def initialize(statement)
    @statement = statement
    @statement_type = categorize(statement)
  end

  def categorize(statement)
    if statement.strip.empty?
      :blow_off
    elsif statement.upcase == statement && (statement =~ /[A-Z]/)
      :shout
    elsif statement.end_with?('?')
      :question
    else
      :non_sequitir
    end
  end
end
