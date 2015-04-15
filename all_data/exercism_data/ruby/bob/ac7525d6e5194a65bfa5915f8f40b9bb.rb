class Bob
  def hey(w)
    a = w.gsub(/\s+/, "")
    if a == ''
      'Fine. Be that way!'
    elsif a.upcase == a and a.downcase != a
      'Whoa, chill out!'
    elsif a[-1] == '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end
