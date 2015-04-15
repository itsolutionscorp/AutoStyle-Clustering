class Bob
  def hey(remark)
    return 'Fine. Be that way!' if remark =~ /\A\s*\Z/
    return 'Whoa, chill out!' if remark == remark.upcase && remark =~ /[a-zA-Z]/
    return 'Sure.' if remark.end_with? '?'
    return 'Whatever.'
  end
end
