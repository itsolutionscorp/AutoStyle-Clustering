def compute(string1, string2)
    join_strands = [string1, string2]
    shorter = join_strands.min do |string1, string2|
      string1.length <=> string2.length
    end

    usable_length = shorter.size

    format_string1 = string1.chars.take(usable_length)
    format_string2 = string2.chars.take(usable_length)

    return 0 if format_string1 == format_string2
    combo = format_string1.zip(format_string2)

    no_duplicate = combo.map do |portion|
      portion.uniq
    end

    no_duplicate.count do |word|
      word.length == 2
    end
  end