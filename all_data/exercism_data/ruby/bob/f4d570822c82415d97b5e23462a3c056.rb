class Bob
  attr_accessor :statement

  def hey statement
    @statement = statement
    respond!
  end

  def respond!
    if shouting? && no_lowercase_words?
      'Woah, chill out!'
    elsif questioning?
      'Sure.'
    elsif empty?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  def empty?
    statement.nil? or statement == ''
  end

  def lowercase_words?
    @statement =~ /(\W[a-z]+\W)/
  end

  def no_lowercase_words?
    !lowercase_words?
  end

  def questioning?
    @statement =~ /\?\Z/
  end

  def shouting?
    @statement =~ /[A-Z]+[^a-z]/
  end

end
