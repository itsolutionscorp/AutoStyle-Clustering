class Proverb

  def initialize(*words, qualifier: adjective = nil)
    @words = words
    @qualifier = qualifier
  end

  def to_s
    lines(@words.reverse)
  end

  def lines(items)
    if items.size == 1
      envoi(@words.first)
    else
      verse(items.pop,items.last) + lines(items)
    end
  end

  def verse(wanted_item, lost_item)
    "For want of a #{wanted_item} the #{lost_item} was lost.\n"
  end

  def envoi(item)
    "And all for the want of a#{qualification} #{item}."
  end

  def qualification
    @qualifier ? " " + @qualifier : ""
  end
end
