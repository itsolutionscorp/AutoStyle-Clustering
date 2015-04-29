class Bob
  def hey what
    case Analiser.string_type what
    when :empty then "Fine. Be that way!"
    when :yieling then 'Woah, chill out!'
    when :question then "Sure."
    else "Whatever."
    end
  end
end

module Analiser

  def string_type text
    text = text.gsub(/\d/, '')
    case
    when empty?(text) then :empty
    when yieling?(text) then :yieling
    when question?(text) then :question
    end
  end

  def empty? text
    text.strip == ''
  end

  def yieling? text
    text =~ /\w/ && text == text.upcase
  end

  def question? text
    string[-1]['?']
  end
end
