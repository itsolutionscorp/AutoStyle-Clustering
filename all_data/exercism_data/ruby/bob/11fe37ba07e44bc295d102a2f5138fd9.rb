class Bob
  def initialize
  end

  def hey(input)
    case
    when input == "" then 'Fine, be that way.'
    when input == input.upcase then 'Woah, chill out!'
    when input.include?("?") then 'Sure.'
    when input.include?("!") then 'Whatever.'
    else
      'Whatever.'
    end
  end
end
