class Proverb

  attr_reader :chain, :qualifier

  def initialize(*chain, qualifier: nil)
    @chain = chain
    @qualifier = qualifier
    @proverb = ""
  end

  def to_s
    assemble_the_story(chain)
    add_the_big_finish
  end

  def assemble_the_story(chain)
    chain.each_cons(2) do |new_thing, thing_i_already_had|
      @proverb += "For want of a #{new_thing} the #{thing_i_already_had} was lost.\n"
    end
  end

  def add_the_big_finish
    @proverb += "And all for the want of a #{qualifier} nail.".squeeze(" ")
  end

end
