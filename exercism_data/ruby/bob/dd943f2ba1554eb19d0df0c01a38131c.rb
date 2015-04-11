class Bob
  def hey(statement)
    statement = Statement.new(statement)
    if statement.shouting?
      shouting_response
    elsif statement.question?
      question_response
    elsif statement.silent?
      silent_response
    else statement
      response
    end
  end

  def shouting_response
    'Woah, chill out!'
  end

  def question_response
    'Sure.'
  end

  def silent_response
    'Fine. Be that way!'
  end

  def response
    'Whatever.'
  end
end

class Statement
  attr_reader :text

  def initialize(text)
    @text = text
  end

  def question?
    text.end_with?('?')
  end

  def silent?
    text.gsub(' ', '').eql?('')
  end

  def shouting?
    !words.empty? && words.all? { |word| letters_all_cap?(word) }
  end

  def words
    remove_non_letters.split(' ')
  end

  def remove_non_letters
    text.gsub(/[\p{P}|\d|\$|\^]/, '')
  end

  def letters_all_cap?(word)
    word.chars.all? { |letter| letter =~ /[A-Z]/ }
  end
end
