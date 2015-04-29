def compute(strand_a, strand_b)
    both = [strand_a.chars, strand_b.chars]
    short = both.min
    long = both.max

    short.zip(long).count { |letter_a, letter_b|
      letter_a != letter_b
    }
  end