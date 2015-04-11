class Bob
  def hey remark
    return 'Fine. Be that way!' if silent? remark
    return 'Whoa, chill out!'   if shout? remark
    return 'Sure.'              if question? remark
    'Whatever.'
  end

private

  def silent? remark
    remark =~ /\A\s*\z/
  end

  def question? remark
    remark.end_with? '?'
  end

  def shout? remark
    remark =~ /\p{Lu}/ && remark == remark.upcase
  end
end
