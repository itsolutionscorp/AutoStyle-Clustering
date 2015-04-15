class Proverb
  def initialize(*list, qualifier: nil)
    @list = list
    @qualifier = " #{qualifier}" if qualifier
  end

  def to_s
    @list.each_cons(2).map do |p,n|
      "For want of a #{p} the #{n} was lost."
    end.push("And all for the want of a#{@qualifier} nail.").join("\n")
  end
end
