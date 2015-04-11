
class Bob
  def hey(sentence)
    if sentence[/\A\s*\z/] != nil
      'Fine. Be that way!'
    elsif sentence.upcase! == nil && sentence[/[a-zA-Z]/] != nil
      'Woah, chill out!'
    elsif sentence[/\?\z/]
      'Sure.'
    else
      'Whatever.'
    end
  end
end
