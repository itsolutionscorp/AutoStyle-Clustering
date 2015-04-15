class Bob
  def hey(text)
    str = text.gsub(/\d/, "")
    if text.gsub(/\s/,'').empty?
      'Fine. Be that way!'
    elsif str == str.upcase && str =~ /[a-zA-Z]/
      'Woah, chill out!'
    elsif text[-1] == '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end
