  class Hamming

    def self.compute(str1, str2)
      join = [str1, str2]
      shorter = join.min do |str1, str2|
        str1.length <=> str2.length
      end
      
      min = shorter.size

      format_str1 = str1.split('').take(min)
      format_str2 = str2.split('').take(min)

      return 0 if format_str1 == format_str2
      combo = format_str1.zip(format_str2)

      no_duplicate = combo.map do |num|
        num.uniq
      end

      no_duplicate.count do |word|
        word.length == 2
      end
    end
  end
