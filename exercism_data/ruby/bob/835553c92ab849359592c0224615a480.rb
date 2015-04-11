class Bob
  def hey(words)
    heard = Remark.new(words)

    if heard.yell?
      'Whoa, chill out!'
    elsif heard.question?
      'Sure.'
    elsif heard.silence?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end

class Remark
  def initialize(string)
    @string = string
  end

  def question?
    string.end_with? '?'
  end

  def silence?
    string.strip.empty?
  end

  def yell?
    forceful? && wordy?
  end

  private

  attr_reader :string

  def forceful?
    string.upcase == string
  end

  def wordy?
    string =~ /[[:alpha:]]/i
  end
end
