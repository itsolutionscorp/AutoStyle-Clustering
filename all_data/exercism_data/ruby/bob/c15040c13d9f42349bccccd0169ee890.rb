class Noisy
  def initialize(stating_argument)
    @stating_argument = stating_argument
  end

  def yelling?
    @stating_argument == @stating_argument.upcase && @stating_argument != @stating_argument.downcase
  end

  def question?
    @stating_argument.end_with?('?')
  end

  def nothing?
    @stating_argument.strip.empty?
  end
end



class Bob
  def hey(stating_argument)
    @noisy = Noisy.new(stating_argument)
    if @noisy.yelling?
      'Woah, chill out!'
    elsif @noisy.question?
      'Sure.'
    elsif @noisy.nothing?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
