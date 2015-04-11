class Bob
  def hey(statement)
    if /^\s+$/ =~ statement || statement.empty?
      'Fine. Be that way!'
    elsif statement == statement.upcase
      'Woah, chill out!'
    elsif statement[-1] == '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end
