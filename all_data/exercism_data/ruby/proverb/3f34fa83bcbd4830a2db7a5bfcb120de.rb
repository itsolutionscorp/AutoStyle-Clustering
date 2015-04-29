class Proverb

  attr_reader :chain, :qualifier

  def initialize(*chain, qualifier: nil)
    @chain = chain
    @qualifier = qualifier
  end

  def to_s
    assemble_the_story(chain) + add_the_big_finish
  end

private

  def assemble_the_story(chain)
    chain.each_cons(2).map do |new_thing, thing_i_already_had|
      "For want of a #{new_thing} the #{thing_i_already_had} was lost.\n"
    end.join
  end

  def add_the_big_finish
    "And all for the want of a #{qualifier} nail.".squeeze(" ")
  end

end
