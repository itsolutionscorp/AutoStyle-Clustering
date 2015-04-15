class Anagram
  private
  attr_accessor :string

  def initialize(val)
    self.string=val
  end

  public
  def match(args)
    args.select{|x| x.chars.map(&:downcase).sort==string.chars.map(&:downcase).sort && x.downcase!=string.downcase}
  end
end
