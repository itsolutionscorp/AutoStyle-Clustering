class Bob
  def hey(input)
    matcher = Matcher.new(input)

    case 
    when matcher.nil_or_empty?
      'Fine. Be that way.'
    when matcher.all_uppercase_or_digits?
      'Woah, chill out!'
    when matcher.question?
      'Sure.' 
    else
      'Whatever.'
    end
  end
end

class Matcher
  def initialize(input)
    @input = input
  end

  def nil_or_empty?
    @input.nil? || @input.empty?
  end

  def all_uppercase_or_digits?
    @input.match( /[A-Z]{2,}/ ) || @input.match(/\d/)
  end

  def question?
    @input.end_with?('?')
  end
end
