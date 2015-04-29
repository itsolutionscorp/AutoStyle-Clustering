class Proverb
  def initialize(*args, qualifier: "")
    @args = args
    @qualifier = " " + qualifier unless qualifier == ""
  end

  def to_s
    proverb = ""
    @args.each_with_index do |word, index|
      proverb += "For want of a #{word} the #{@args[index+1]} was lost.\n" unless @args[index+1].nil?
    end
    return proverb + "And all for the want of a#{@qualifier} nail."
  end

end
