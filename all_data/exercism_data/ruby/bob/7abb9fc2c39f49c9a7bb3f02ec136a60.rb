class Bob
  def hey(str)
    comment = if is_nothing?(str)
      'Fine. Be that way!'
    elsif is_yelling?(str)
      'Woah, chill out!'
    elsif is_asking?(str)
      'Sure.'
    else
      'Whatever.'
    end
    comment
  end

  private

  def is_yelling?(str)
    str == str.upcase
  end

  def is_asking?(str)
    str.index(/\?\z/)
  end

  def is_nothing?(str)
    str.nil? || str.strip.empty?
  end
end
