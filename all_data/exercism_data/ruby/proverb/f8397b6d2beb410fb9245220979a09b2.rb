class Proverb

  def initialize(*proverbs, qualifier: nil)
    @proverbs = proverbs
    @qualifier = qualifier
  end

  def to_s
    proverb_string = ""
    ((@proverbs.length)-1).times do |x|
      proverb_string << "For want of a #{@proverbs[x]} the #{@proverbs[x+1]} was lost.\n"
    end
    if @qualifier == nil
      proverb_string << "And all for the want of a #{@proverbs[0]}."
    else
      proverb_string << "And all for the want of a #{@qualifier} #{@proverbs[0]}."
    end
    proverb_string
  end

end
