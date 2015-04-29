class Bob
  def hey(str)
    str.strip!
    case
    when str.empty? then 'Fine. Be that way!'
    when all_is_uppercase?(str) && contain_letters?(str)
      'Woah, chill out!'
    when str.end_with?('?') then 'Sure.'
    else 'Whatever.'
    end
  end

  private

  def contain_letters?(str)
    str=~/[A-Za-z]/
  end

  def all_is_uppercase?(str)
    str.upcase==str
  end
end
