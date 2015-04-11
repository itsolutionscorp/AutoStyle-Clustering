class House

  def verse(num, str="")
    return str += "\n" if num == 0 
    str = "This is the " if str.empty?
    str << case num
      when 1
        "house that Jack built." 
      when 2
        "malt that lay in the "
      when 3
        "rat that ate the "
      when 4
        "cat that killed the "
      when 5
        "dog that worried the "
      when 6
        "cow with the crumpled horn that tossed the "
      when 7
        "maiden all forlorn that milked the "
      when 8
        "man all tattered and torn that kissed the "
      when 9
        "priest all shaven and shorn that married the "
      when 10
        "rooster that crowed in the morn that woke the "
      when 11
        "farmer sowing his corn that kept the "
      when 12
        "horse and the hound and the horn that belonged to the "
      end
    # str += add
    verse(num-1, str)
  end

  def verses(min, max) 
    (min..max).each_with_object(""){ |i, song| song << verse(i) + "\n"}
  end
end
