class Proverb
  attr_reader :word_list

  def initialize(*args, **opts)
    @word_list = args
    @qualifier = opts[:qualifier]
    @qualifier += " " unless @qualifier.nil?
  end

  def to_s
    @proverb = ""
    (0..@word_list.length-2).each do |i|
      @proverb += "For want of a #{@word_list[i]} the #{@word_list[i+1]} was lost.\n"
    end
    @proverb += "And all for the want of a #{@qualifier}#{@word_list[0]}."
  end
end
