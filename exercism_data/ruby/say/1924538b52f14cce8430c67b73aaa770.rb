class Say
  def initialize(number)
    @number = number
  end

  def in_english
    EnglishSay.new(@number).words
  end

  private

  class EnglishSay
    MIN_SAY = 0
    MAX_SAY = 1e12-1

    def initialize(number)
      unless number.between?(MIN_SAY, MAX_SAY)
        raise ArgumentError, "I won't say #{number}!"
      end
      @words = ""
      compute_words(number)
    end

    attr_reader :words

    private

    def compute_words(number)
      if number.zero?
        append_word WORD_ZERO
      else
        multiples(number)
      end
    end

    def append_word(word, insert_dash = false)
      unless words.empty?
        words << (insert_dash ? "-" : " ")
      end
      words << word
    end

    def multiples(remainder)
      WORDS_MULTIPLES.each do |multiple, word|
        quotient, remainder = remainder.divmod(multiple)
        if quotient > 0
          multiples(quotient)
          append_word word
        end
      end

      below_hundred(remainder)
    end

    def below_hundred(remainder)
      insert_dash = false
      WORDS_BELOW_HUNDRED.each do |n, word|
        if remainder >= n
          append_word word, insert_dash
          remainder -= n

          insert_dash = n.between? 20, 99
        end
      end
    end

    WORD_ZERO = "zero"
    WORDS_BELOW_HUNDRED = {
      90 => "ninety",
      80 => "eighty",
      70 => "seventy",
      60 => "sixty",
      50 => "fifty",
      40 => "forty",
      30 => "thirty",
      20 => "twenty",
      19 => "nineteen",
      18 => "eighteen",
      17 => "seventeen",
      16 => "sixteen",
      15 => "fifteen",
      14 => "fourteen",
      13 => "thirteen",
      12 => "twelve",
      11 => "eleven",
      10 => "ten",
      9  => "nine",
      8  => "eight",
      7  => "seven",
      6  => "six",
      5  => "five",
      4  => "four",
      3  => "three",
      2  => "two",
      1  => "one",
    }

    WORDS_MULTIPLES = {
      1e9 => "billion",
      1e6 => "million",
      1e3 => "thousand",
      100 => "hundred",
    }
  end
end
