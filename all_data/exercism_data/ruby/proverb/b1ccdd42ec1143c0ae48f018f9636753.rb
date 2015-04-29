class Proverb
  def initialize(*w, qualifier: "")
    @lost_for_want_of = w
    @and_all_for = w[0]
    @qualifier = !qualifier.empty? ? " #{qualifier}" : qualifier
  end

  def to_s
    p = String.new

    @lost_for_want_of.each_cons(2) do |w1, w2|
      p = p.+ "For want of a #{w1} the #{w2} was lost.\n"
    end
    p = p.+ "And all for the want of a#{@qualifier} #{@and_all_for}."

    return p
  end
end
