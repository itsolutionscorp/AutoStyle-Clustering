class Proverb
  def initialize(*words)
    if words.last.is_a?(Hash)
      @qualifier = "#{words.pop[:qualifier]} "
    else
      @qualifier = ""
    end
    @qualifier << words.first
    @words = words
  end

  def to_s
    first = @words.rotate(-1).drop(1)
    second = @words.drop(1)
    zip = first.zip(second)
    str = zip.reduce("") do |acc, pair|
      acc << "For want of a #{pair[0]} the #{pair[1]} was lost.\n"
    end
    str << "And all for the want of a #{@qualifier}."
  end


end
