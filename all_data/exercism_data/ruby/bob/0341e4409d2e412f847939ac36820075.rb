class Bob
  def hey(statement)
    case Statement.new(statement)
    when :shouted?.to_proc then "Whoa, chill out!"
    when :asked?.to_proc   then "Sure."
    when :silent?.to_proc  then "Fine. Be that way!"
    else                        "Whatever."
    end
  end
end

class Statement

  attr_reader :statement
  def initialize(statement)
    @statement = statement
  end

  def shouted?
    has_letter? && is_upcase?
  end

  def asked?
    ends_with_question_mark?
  end

  def silent?
    is_blank? || is_only_white_space? 
  end

  private
  def has_letter?
    !!(statement =~ /[a-zA-Z]/)
  end

  def is_upcase?
    statement == statement.upcase
  end

  def is_only_white_space?
    statement == statement.slice(/\s+/)
  end

  def is_blank?
    statement.empty?
  end

  def ends_with_question_mark?
    statement.end_with?('?')
  end
end
