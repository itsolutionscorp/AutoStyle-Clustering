class Bob

  DEFAULT = "Whatever."
  RESPONSES = {:nothing => "Fine. Be that way!",
               :shout => "Woah, chill out!",
               :question => "Sure."}

  def hey(string)

    bobstring = BobString.new(string)

    if bobstring.nothing?
      respond[:nothing]
    elsif bobstring.shout?
      respond[:shout]
    elsif bobstring.question?
      respond[:question]
    else
      respond[:default]
    end
  end

  def respond
    @respond ||= Hash.new(DEFAULT).merge!(RESPONSES)
  end
end

class BobString

  def initialize(string)
    #nil.to_s gives an empty string
    @string = string.to_s
  end

  def nothing?
    @string.strip.empty?
  end

  def shout?
    @string.upcase == @string
  end

  def question?
    @string.end_with?('?')
  end

end
