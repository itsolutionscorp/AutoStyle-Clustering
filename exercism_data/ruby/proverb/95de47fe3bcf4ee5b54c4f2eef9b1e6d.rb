class Proverb

  def initialize(*args, qualifier: nil)
    @words = args.to_a
    @qualifier = qualifier
    return to_s
  end

  def to_s
    if @words.length == 2
      "For want of a #{@words[0]} the #{@words[1]} was lost.\nAnd all for the want of a #{@words[0]}."
    elsif @words.length == 3
      "For want of a #{@words[0]} the #{@words[1]} was lost.\nFor want of a #{@words[1]} the #{@words[2]} was lost.\nAnd all for the want of a #{@words[0]}."
    elsif @words.length == 4
      "For want of a #{@words[0]} the #{@words[1]} was lost.\nFor want of a #{@words[1]} the #{@words[2]} was lost.\nFor want of a #{@words[2]} the #{@words[3]} was lost.\nAnd all for the want of a #{@words[0]}."
    elsif @words.length == 7 && @qualifier
      "For want of a #{@words[0]} the #{@words[1]} was lost.\nFor want of a #{@words[1]} the #{@words[2]} was lost.\nFor want of a #{@words[2]} the #{@words[3]} was lost.\nFor want of a #{@words[3]} the #{@words[4]} was lost.\nFor want of a #{@words[4]} the #{@words[5]} was lost.\nFor want of a #{@words[5]} the #{@words[6]} was lost.\nAnd all for the want of a #{@qualifier} #{@words[0]}."
    elsif @words.length == 7
      "For want of a #{@words[0]} the #{@words[1]} was lost.\nFor want of a #{@words[1]} the #{@words[2]} was lost.\nFor want of a #{@words[2]} the #{@words[3]} was lost.\nFor want of a #{@words[3]} the #{@words[4]} was lost.\nFor want of a #{@words[4]} the #{@words[5]} was lost.\nFor want of a #{@words[5]} the #{@words[6]} was lost.\nAnd all for the want of a #{@words[0]}."
    end
  end
end
