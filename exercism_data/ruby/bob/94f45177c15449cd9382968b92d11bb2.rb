class Bob
  def hey(str)
    return 'Fine. Be that way!' if is_blank?(str)
    return 'Woah, chill out!'   if is_shouting?(str)
    return 'Sure.'              if is_question?(str)
    'Whatever.'
  end

  private

  def is_question?(str)
    str[-1] == '?'
  end

  def is_blank?(str)
    str.gsub(/\s+/, '') == ''
  end

  def is_shouting?(str)
    chars = str.scan(/[a-zA-Z]/)
    !chars.empty? && chars.all?{ |char| char =~ /[A-Z]/ }
  end
end
