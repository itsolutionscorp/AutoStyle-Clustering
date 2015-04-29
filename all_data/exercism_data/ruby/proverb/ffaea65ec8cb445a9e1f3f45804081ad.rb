class Proverb
  def initialize(*words)
    @qualifier = words.pop[:qualifier] if words.last.is_a? Hash
    @qualifier = "#{@qualifier} "      if @qualifier

    @proverb   = *words
  end

  def to_s
    string = ''
    @proverb.each_cons(2) do |want, lost|
      string += "For want of a #{want} the #{lost} was lost.\n" 
    end

    string += "And all for the want of a #{@qualifier}#{@proverb.first}."
    string
  end
end
