class Raindrops
  FACTOR_MAP = { 3 => "Pling",
                 5 => "Plang",
                 7 => "Plong" }

  def self.convert(candidate)
    number_as_words = factors_to_words(candidate)

    if number_as_words.empty?
      candidate.to_s
    else
      number_as_words.join ""
    end

  end

  def self.factors_to_words(candidate)
    FACTOR_MAP.flat_map do |factor, word|
      word if divisible(candidate, factor)
    end.compact
  end

  def self.divisible(candidate, factor)
    candidate % factor == 0
  end
end
