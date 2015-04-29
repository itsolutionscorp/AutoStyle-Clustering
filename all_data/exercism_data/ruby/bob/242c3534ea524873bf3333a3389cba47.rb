class Bob
  def initialize

  end

  def hey(input)
    if input.delete(' ').empty?
      return 'Fine. Be that way!'
    elsif input.match(/[a-z]/i) && input == input.upcase
      return 'Woah, chill out!'
    elsif input.end_with?('?')
      return 'Sure.'
    else
      return 'Whatever.'
    end
  end
end
