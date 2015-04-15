class Integer
  # consider, converting 2448 to roman for this example.
  def to_roman
    roman, num = "", self
    roman_map = { 1 => "I", 5 => "V", 10 => "X", 50 => "L", 100 => "C", 500 => "D", 1000 => "M" }

    # loop over the break points, i.e. 1, 5, 10, 50, etc.
    roman_map.keys.sort.reverse.each do |iter|
      # reduce number by extracting the highest possible roman numerals
      # i.e. reduce 2448 to 448 and append 'MM' to our string.
      roman += roman_map[iter] * (num / iter)
      num   %= iter

      # find the power of ten just lower than the current iterator
      # useful to convert numbers like 40 to 'XL'
      # Therefore, if `iter` is 100 or 50, `altiter` will be 10
      altiter = 3.downto(0).detect{ |i| 10 ** i < iter }
      altiter = 10 ** altiter if altiter

      # if `altiter` is present, and number lies between the given range,
      # we have a candidate similar to 'XL', or 'CM'
      # e.g. if `iter` is 100, we only proceed if the reduced number >= 90
      if altiter && iter - altiter <= num
        roman += "#{roman_map[altiter]}#{roman_map[iter]}"
        num   -= (iter - altiter)
      end
    end

    # we have our roman representation now, return it
    roman
  end
end
