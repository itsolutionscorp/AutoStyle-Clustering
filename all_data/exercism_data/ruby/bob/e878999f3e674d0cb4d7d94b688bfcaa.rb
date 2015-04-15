class Bob
  def hey(heard)
    heard = Remark.new(heard)

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
    '?' == @string[-1]
  end

  def silence?
    0 == @string.gsub(/\s/, '').length
  end

  def yell?
    forceful? && wordy?
  end

  private

  def forceful?
    @string.upcase == @string
  end

  def wordy?
    @string =~ /[[:alpha:]]/i
  end
end
