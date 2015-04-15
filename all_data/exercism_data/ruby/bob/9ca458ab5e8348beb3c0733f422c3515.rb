class TeenagerTalk
  attr_accessor :string

  def self.translate(input)
    @string = input
    case
    when not_saying_anything?  then "Fine. Be that way!"
    when yelling?              then "Woah, chill out!"
    when a_question?           then "Sure."
    when something_else?       then "Whatever."
    else raise "The translator module has failed to translate. Oh noes!!!"
    end
  end

  private
  def self.not_saying_anything?
    @string.nil? || @string.strip.empty?
  end

  def self.yelling?
    @string.upcase == @string
  end

  def self.a_question?
    @string.end_with? "?"
  end

  def self.something_else?
    true
  end
end

class Bob
  def hey(input)
    TeenagerTalk.translate input
  end
end
