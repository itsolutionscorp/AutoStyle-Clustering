class Bob
  def hey(remark)
    # ignore line breaks
    remark.gsub!(/[\r\n]/, '')

    case
    when remark =~ /^\s*$/
      'Fine. Be that way!'
    when remark !~ /[a-z]+/ && remark =~ /[A-Z]+/
      'Whoa, chill out!'
    when remark =~ /\?$/
      'Sure.'
    else
      'Whatever.'
    end
  end
end
