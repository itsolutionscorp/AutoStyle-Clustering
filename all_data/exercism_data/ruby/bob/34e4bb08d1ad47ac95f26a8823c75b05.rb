class Bob
  def hey statement
    return be_that_way if Statement.empty? statement
    return chill_out if Statement.shout? statement
    return whatever if Statement.declaration? statement
    return sure if Statement.question? statement
  end

  private
  def whatever
    'Whatever.'
  end

  def chill_out
    'Woah, chill out!'
  end

  def sure
    'Sure.'
  end

  def be_that_way
    'Fine. Be that way!'
  end
end

class Statement
  def self.question? statement
    statement[-1..-1] == '?'
  end

  def self.plain? statement
    statement[-1..-1] == '.'
  end

  def self.exclamation? statement
    statement[-1..-1] == '!'
  end

  def self.declaration? statement
    exclamation?(statement) || plain?(statement)
  end

  def self.shout? statement
    statement == statement.upcase
  end

  def self.empty? statement
    statement.nil? || statement.empty? || statement.gsub(' ', '').empty?
  end
end
