class Bob
  def initialize
  end

  def hey(str)
    case
    when str == str.upcase && str.downcase =~ /[a-z]/
      return 'Woah, chill out!'
    when str[-1] == '?'
      return 'Sure.'
    when str.strip == ''
      return 'Fine. Be that way!'
    else
      return 'Whatever.'
    end
  end
end
