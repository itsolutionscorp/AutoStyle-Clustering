class Bob
  def hey(input)
    angst = Angst.new(input)

    case 
    when angst.towards_silence?
      'Fine. Be that way.'
    when angst.towards_aggression?
      'Woah, chill out!'
    when angst.towards_question?
      'Sure.' 
    else
      'Whatever.'
    end
  end
end

class Angst
  def initialize(input)
    @input = input
  end

  def towards_silence?
    @input.nil? || @input.empty?
  end

  def towards_aggression?
    @input.match( /[A-Z]{2,}/ ) || @input.match(/\d/)
  end

  def towards_question?
    @input.end_with?('?')
  end
end
