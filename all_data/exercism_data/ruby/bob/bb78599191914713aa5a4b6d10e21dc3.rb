class Bob
  def initialize
  end

  def hey(str)
    case
    when str == str.upcase && str =~ /[[:alpha:]]/
      return 'Woah, chill out!'
    when str[-1] == '?'
      return 'Sure.'
    when str.strip.empty?
      return 'Fine. Be that way!'
    else
      return 'Whatever.'
    end
  end
end
