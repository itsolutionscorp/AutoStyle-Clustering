class Proverb
  def initialize(*args, qualifier: nil)
    @qualifier = qualifier
    @scarcities = args
  end

  def to_s
    recount_chain_reaction.join("\n")
  end

  private

  def recount_chain_reaction
    @scarcities.each_cons(2).map do |lack, forfeit|
      recount_loss(lack, forfeit)
    end
    .push(recount_catalyst)
  end

  def recount_loss(lack, forfeit)
    "For want of a #{lack} the #{forfeit} was lost."
  end

  def recount_catalyst
    "And all for the want of a #{qualifier}#{undoing}."
  end

  def undoing
    @scarcities.first
  end

  def qualifier
    @qualifier + " " if @qualifier
  end
end
