module PigLatin
  class << self

    NO_SWAP_CASES = %w(a e i o u yt xr)       # add "ay"
    SWAP_CASES    = %w(ch qu squ thr th sch)  # swap front letters and add "ay"
                                              # otherwise swap first consonante and add "ay"

    def swap_letters(word)
      SWAP_CASES.each do |c|
        return swap(word, c.length) if word.start_with?(c)
      end
    end

    def translate(text)
      text.split.map { |word| to_pig_latin(word) }.join(' ')
    end

    def to_pig_latin(word)
      if word.start_with?(*NO_SWAP_CASES)
        return word += "ay"
      elsif word.start_with?(*SWAP_CASES)
        return swap_letters(word) + "ay"
      else
        return swap(word, 1) + "ay"
      end
    end

    def swap(word, count)
      first_chars = word[0...count]
      word[count..-1] + first_chars
    end
  end
end
