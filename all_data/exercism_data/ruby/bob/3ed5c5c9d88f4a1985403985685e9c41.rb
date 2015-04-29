class Bob
  def hey(input)
    @input = input
    @stripped = strip_input
    respond
  end

  private

  attr_reader :input, :stripped

  def respond
    if silence?
      'Fine. Be that way!'
    elsif question?
      'Sure.'
    elsif anger?
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end

  def question?
    input.end_with?('?') && !anger?
  end

  def silence?
    input.strip == ""
  end

  def anger?
    stripped.upcase == stripped && stripped.length > 1
  end

  def strip_input
    allowed_chars = 'A'..'z'
    input.each_char.select{ |c|
      allowed_chars.include?(c) || c == '?'
    }.join
  end
end
