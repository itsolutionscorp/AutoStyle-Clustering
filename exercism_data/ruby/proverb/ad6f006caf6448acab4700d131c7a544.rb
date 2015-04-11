class Proverb

  def initialize(*words, qualifier: {})
    @qualifier = qualifier
    @word_list = words
  end

  def to_s
    storage_array = []
    i = 0
    while i < (@word_list.length - 1)
      first_part = "For want of a #{@word_list[i]} the #{@word_list[i+1]} was lost.\n"
      i += 1
      storage_array << first_part
    end

    if @qualifier != {}
      proverb = storage_array + ["And all for the want of a #{@qualifier} #{@word_list[0]}."]
      proverb.join
    else
      proverb = storage_array + ["And all for the want of a #{@word_list[0]}."]
      proverb.join
    end
  end

end
