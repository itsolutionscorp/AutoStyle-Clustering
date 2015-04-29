class Bob
  def hey(statement)
    Response.create(statement).respond
  end
end

class Response
  def self.create(statement)
    if shouting?(statement)
      ExclamatoryResponse.new
    elsif question?(statement)
      InterrogatoryResponse.new
    elsif silent?(statement)
      SilentResponse.new
    else statement
      Response.new
    end
  end

  def respond
    'Whatever.'
  end

  def self.question?(statement)
    statement.chars.last.eql?('?')
  end

  def self.silent?(statement)
    statement.eql?('') || statement.gsub(' ', '').eql?('')
  end

  def self.shouting?(statement)
    words = words_in_statement(statement)
    !words.empty? && words.all? { |word| letters_all_cap?(word) }
  end

  def self.words_in_statement(statement)
    remove_non_letters(statement).split(' ')
  end

  def self.remove_non_letters(statement)
    statement.gsub(/[\p{P}|\d|\$|\^]/, '')
  end

  def self.letters_all_cap?(word)
    word.chars.all? { |letter| letter =~ /[A-Z]/ }
  end
end

class ExclamatoryResponse < Response
  def respond
    'Woah, chill out!'
  end
end

class InterrogatoryResponse < Response
  def respond
    'Sure.'
  end
end

class SilentResponse < Response
  def respond
    'Fine. Be that way!'
  end
end
