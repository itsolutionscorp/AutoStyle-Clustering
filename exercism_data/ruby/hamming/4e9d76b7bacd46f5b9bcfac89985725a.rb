class Hamming
  def self.compute(string1, string2)
    usable_length = shorter(string1, string2).size

    format_string1 = format_string(string1, usable_length)
    format_string2 = format_string(string2, usable_length)

    zipped = combo(format_string1, format_string2)

    final_count(zipped)
  end

  def self.shorter(string1, string2)
    join = [string1, string2]
    join.min { |string1, string2| string1.length <=> string2.length }
  end

  def self.format_string(string, length)
    string.chars.take(length)
  end

  def self.combo(str1, str2)
    str1.zip(str2)
  end

  def self.no_duplicate(zipped)
    zipped.map { |portion| portion.uniq}
  end

  def self.final_count(zipped)
    no_duplicate(zipped).count do |word|
      word.length == 2
    end
  end
end
